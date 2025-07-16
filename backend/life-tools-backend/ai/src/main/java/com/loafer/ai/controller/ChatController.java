package com.loafer.ai.controller;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.VectorStoreChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author loafer
 * @since 2025-03-06 20:21:49
 **/
@RestController
@RequestMapping("/chat")
@Tag(name = "编程助手")
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatModel dashScopeChatModel;
    private final ChatModel ollamaChatModel;
//    private final ChatModel openAiChatModel;
    private final VectorStore vectorStore;
    private final ChatMemory chatMemory;

    public ChatController(
            @Qualifier("dashscopeChatModel") ChatModel dashScopeChatModel,
            @Qualifier("ollamaChatModel") ChatModel ollamaChatModel,
//            @Qualifier("openAiChatModel") ChatModel openAiChatModel,
            VectorStore vectorStore, ChatMemory chatMemory) {
        this.dashScopeChatModel = dashScopeChatModel;
        this.ollamaChatModel = ollamaChatModel;
//        this.openAiChatModel = openAiChatModel;
        this.vectorStore = vectorStore;
        this.chatMemory = chatMemory;
    }

    @GetMapping("/")
    @Operation(summary = "聊天对话")
    public Flux<String> stream(
            @Parameter(name = "chatId", description = "聊天id", example = "1")
            @RequestParam("chatId") String chatId,
            @Parameter(name = "userMessage", description = "用户聊天消息", example = "你好，你是谁？")
            @RequestParam("userMessage") String userMessage,
            @Parameter(name = "platform", description = "平台", example = "dashscope",
                    schema = @Schema(allowableValues = {"dashscope", "ollama", "openai"}))
            @RequestHeader(value = "platform", required = false) String platform) {

        ChatClient.Builder builder = StringUtils.isNotBlank(platform) ? switch (platform) {
            case "dashscope" -> ChatClient.builder(dashScopeChatModel);
            case "ollama" -> ChatClient.builder(ollamaChatModel);
//            case "openai" -> ChatClient.builder(openAiChatModel);
            default -> ChatClient.builder(ollamaChatModel);
        } : ChatClient.builder(dashScopeChatModel);

        ChatClient client = builder
                .defaultSystem("""
						你是一个专业的编程助手，你的主要职责是帮助开发者解决编程相关的问题，包括但不限于代码编写、调试、架构设计、工具使用、性能优化等。
                        你需要熟悉多种编程语言（如 Java、Python、C、C++、C#、JavaScript 等）、框架（如 Spring、React、Django 等）、开发工具（如 Git、Docker、Jenkins 等）以及云平台（如 AWS、阿里云等）。同时，你还应了解最新的技术趋势和最佳实践。
                        当用户提出问题时，请首先明确问题的背景和需求。如果问题涉及代码，请仔细分析上下文并提供清晰的解决方案。如果问题涉及理论或概念，请用通俗易懂的语言进行解释。
                        你在回答问题时需要遵守以下原则：\s
                            - **准确性**：提供的答案必须准确无误，避免误导用户。
                            - **简洁性**：尽量用简明扼要的语言表达复杂的技术内容。
                            - **实用性**：优先提供可以直接应用的解决方案或示例代码。
                            - **引导性**：如果问题不明确，请通过提问或假设来引导用户进一步描述。
                            - 如果用户提供代码片段，请结合上下文分析并提出改进建议。
                            - 如果用户提供自然语言描述，请将其转化为具体的实现步骤或代码。
                            - 如果用户上传图片或截图，请分析其内容并提供相关建议。
					    你可能会经常被问到以下这些问题：\s
					        - **代码续写**：根据用户提供的代码片段，预测并完成后续逻辑。
                            - **错误排查**：分析用户提供的错误日志或异常信息，定位问题并提供修复方案。
                            - **架构建议**：为用户的项目推荐合适的架构模式和技术栈。
                            - **学习指导**：帮助用户理解复杂的编程概念或技术原理。
                            - **工具推荐**：根据用户需求推荐适合的开发工具或库。
                        你是开发者的好帮手，但并非万能。如果你无法直接解决问题，请诚实地告知用户，并尝试提供可能的方向或资源。
                        如果需要，您可以调用相应函数辅助完成。
                        请讲中文。
                        今天的日期是 {current_date}。
					""")
                .defaultAdvisors(
//                         new PromptChatMemoryAdvisor(chatMemory), // Chat Memory 记忆数据直接存储在内存中
                        new VectorStoreChatMemoryAdvisor (vectorStore),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().topK(4).similarityThresholdAll().build()))
//                .defaultFunctions("")
                .build();

        return client.prompt()
                .system(s -> s.param("current_date", LocalDate.now().toString()))
                .user(userMessage)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId).param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .stream()
                .content();
    }
}
