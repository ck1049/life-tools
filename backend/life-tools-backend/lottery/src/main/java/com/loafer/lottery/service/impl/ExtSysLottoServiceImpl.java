package com.loafer.lottery.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loafer.common.utils.ThreadPoolUtil;
import com.loafer.lottery.model.Dictionary;
import com.loafer.lottery.service.IDictionaryService;
import com.loafer.lottery.service.IExtSysLottoService;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author loafer
 * @since 2023-11-04 21:48:19
 **/
@Service
@AllArgsConstructor
public class ExtSysLottoServiceImpl implements IExtSysLottoService {

    RestTemplate restTemplate;
    IDictionaryService dictionaryService;

    @Override
    public List<OfficialLottoInfoVO> getLottoInfo(Integer pageNo, Integer pageSize) throws InterruptedException {
        Dictionary one = dictionaryService.getOne(Wrappers.lambdaQuery(Dictionary.class)
                .eq(Dictionary::getModule, "EXT_SYS_URL")
                .eq(Dictionary::getKeyName, "LOTTO"));
        List<OfficialLottoInfoVO> result = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(pageSize);
        for (int i = 0; i < pageSize; i++) {
            ThreadPoolUtil.getExecutorService("getLottoInfoFromExtSys")
                    .submit(new getLottoInfoRunnable(pageNo + i, one.getValue(), latch, restTemplate, result));
        }
        latch.await();
        return result;
    }

    @Slf4j
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
