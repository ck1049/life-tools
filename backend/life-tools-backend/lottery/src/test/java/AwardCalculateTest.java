import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loafer.lottery.util.AbstractLATUtils;
import com.loafer.lottery.util.LottoUtils;

import java.util.*;

/**
 * @author loafer
 * @since 2024-05-16 01:34:30
 **/
public class AwardCalculateTest {


    public static void main(String[] args) throws JsonProcessingException {
        int[] awardRedBalls = new int[] {7, 17, 22, 30, 34};
        int[] awardBlueBalls = new int[] {5, 7};

        AbstractLATUtils lottoUtils = new LottoUtils();
        long start = System.currentTimeMillis();
        Set<Integer>[] randomLottery = lottoUtils.randomLottery(6, 3);
        int[] myRedBalls = randomLottery[0].stream().mapToInt(Integer::intValue).sorted().toArray();
        int[] myBlueBalls = randomLottery[1].stream().mapToInt(Integer::intValue).sorted().toArray();
        long end = System.currentTimeMillis();

        System.out.println("开奖号码：" + Arrays.toString(awardRedBalls) + " " + Arrays.toString(awardBlueBalls));
        System.out.println("我的号码：" + Arrays.toString(myRedBalls) + " " + Arrays.toString(myBlueBalls));
        System.out.println("号码生成耗时：" + (end - start) + "ms.");

        long myRedBallNum = myRedBalls.length;
        long myBlueBallNum = myBlueBalls.length;
        long matchRedBallNum = lottoUtils.matchBallNum(awardRedBalls, myRedBalls);
        long matchBlueBallNum = lottoUtils.matchBallNum(awardBlueBalls, myBlueBalls);
        start = System.currentTimeMillis();
        Map<Long, Long> awardLevelNumMap = lottoUtils.calculateAwardLevelNumMap(myRedBallNum, myBlueBallNum, matchRedBallNum, matchBlueBallNum);
        end = System.currentTimeMillis();
        System.out.println(new ObjectMapper().writeValueAsString(awardLevelNumMap));
        System.out.println("计算耗时：" + (end - start) + "ms.");
    }

}

