package com.loafer.lottery.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loafer.common.utils.ThreadPoolUtil;
import com.loafer.lottery.model.Dictionary;
import com.loafer.lottery.service.IDictionaryService;
import com.loafer.lottery.service.IExtSysLotteryService;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import com.loafer.lottery.vo.OfficialTwoColorBallInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author loafer
 * @since 2023-11-04 21:48:19
 **/
@Slf4j
@Service
@AllArgsConstructor
public class ExtSysLotteryServiceImpl implements IExtSysLotteryService {

    RestTemplate restTemplate;
    IDictionaryService dictionaryService;

    @Override
    public List<OfficialLottoInfoVO> getLottoInfo(Integer pageNo, Integer pageSize) throws InterruptedException {
        Dictionary one = dictionaryService.getOne(Wrappers.lambdaQuery(Dictionary.class).eq(Dictionary::getModule, "EXT_SYS_URL").eq(Dictionary::getKeyName, "LOTTO"));
        List<OfficialLottoInfoVO> result = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(pageSize);
        for (int i = 0; i < pageSize; i++) {
            // pageSize始终是1，每次只移动pageNo，多线程提升并发效率
            ThreadPoolUtil.getExecutorService("getLottoInfoFromExtSys").submit(new getLottoInfoRunnable(pageNo + i, one.getValue(), latch, restTemplate, result));
        }
        latch.await();
        return result;
    }

    @Override
    public OfficialTwoColorBallInfoVO getTwoColorBallInfo(Integer pageNo, Integer pageSize) throws IOException {
        Dictionary one = dictionaryService.getOne(Wrappers.lambdaQuery(Dictionary.class).eq(Dictionary::getModule, "EXT_SYS_URL").eq(Dictionary::getKeyName, "TWO_COLOR_BALL"));
        String url = one.getValue().replace("{pageNo}", String.valueOf(pageNo)).replace("{pageSize}", String.valueOf(pageSize));
        log.info("=====getTwoColorBallInfo=====" + url);

        HttpEntity<HttpHeaders> entity = getHttpHeadersHttpEntity();
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String response = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
        return new ObjectMapper().readValue(response, OfficialTwoColorBallInfoVO.class);
    }

    private static HttpEntity<HttpHeaders> getHttpHeadersHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Cache-Control", "no-cache");
        headers.add("User-Agent", "PostmanRuntime-ApipostRuntime/1.1.0");
        headers.add("Accept-Encoding", "gzip");
        headers.add("Accept-Encoding", "deflatebr");
        headers.add("Accept-Encoding", "br");
        headers.add("Connection", "keep-alive");
        headers.add("cookie", "HMF_CI=HMF_CI=36a6fb5003978e139d0af4e8f2d16a8878e41e962f9eeb0838656d33dda5b039ac205838044a35bb414ae09d7a4f9a3961e6150e14e12100b49154e38fce735423");

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
        return entity;
    }

    private static class getLottoInfoRunnable implements Runnable {
        private int pageNo;
        private String url;
        private CountDownLatch latch;
        private RestTemplate restTemplate;
        private List<OfficialLottoInfoVO> result;

        public getLottoInfoRunnable(int pageNo, String url, CountDownLatch latch, RestTemplate restTemplate, List<OfficialLottoInfoVO> result) {
            this.pageNo = pageNo;
            this.url = url;
            this.latch = latch;
            this.restTemplate = restTemplate;
            this.result = result;
        }

        @Override
        public void run() {
            String url = this.url.replace("{pageNo}", String.valueOf(this.pageNo));
            log.info("=====getLottoInfoUrl=====" + url);
            this.result.add(this.restTemplate.getForObject(url, OfficialLottoInfoVO.class));
            this.latch.countDown();
        }
    }
}
