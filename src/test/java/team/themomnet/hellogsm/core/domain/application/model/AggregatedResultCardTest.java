//package team.themomnet.hellogsm.core.domain.application.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AggregatedResultCardTest {
//
//    @Test
//    void testPercentileRankCalculation() {
//        // Example usage for testing
//        BigDecimal totalScore = new BigDecimal("250");
//        AbstractAggregatedResultCard card = new GraduateApplication.GraduateAggregatedResultCard(totalScore);
//
//        // Example expected values
//        BigDecimal expectedPercentileRank = new BigDecimal("16.667");
//
//        assertEquals(expectedPercentileRank, card.getPercentileRank());
//    }
//
//    @Test
//    void testPercentileRankCalculationWithMaxScore() {
//        BigDecimal totalScore = new BigDecimal("300");
//        AbstractAggregatedResultCard card = new GraduateApplication.GraduateAggregatedResultCard(totalScore);
//
//        // The percentile rank for max score should be 0
//        BigDecimal expectedPercentileRank = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_UP);
//
//        assertEquals(expectedPercentileRank, card.getPercentileRank());
//    }
//
//    @Test
//    void testPercentileRankCalculationWithZeroScore() {
//        BigDecimal totalScore = BigDecimal.ZERO;
//        AbstractAggregatedResultCard card = new GraduateApplication.GraduateAggregatedResultCard(totalScore);
//
//        // The percentile rank for zero score should be 0
//        BigDecimal expectedPercentileRank = BigDecimal.valueOf(100).setScale(3, RoundingMode.HALF_UP);
//
//        assertEquals(expectedPercentileRank, card.getPercentileRank());
//    }
//
//    @Test
//    void testPercentileRankCalculationWithHalfMaxScore() {
//        BigDecimal totalScore = new BigDecimal("150");
//        AbstractAggregatedResultCard card = new GraduateApplication.GraduateAggregatedResultCard(totalScore);
//
//        // The percentile rank for half of max score should be 50.000
//        BigDecimal expectedPercentileRank = new BigDecimal("50.000");
//
//        assertEquals(expectedPercentileRank, card.getPercentileRank());
//    }
//
//    @Test
//    void testPercentileRankCalculationWithRandomScore() {
//        BigDecimal totalScore = new BigDecimal("200");
//        AbstractAggregatedResultCard card = new GraduateApplication.GraduateAggregatedResultCard(totalScore);
//
//        // The percentile rank for a random score can be calculated based on your formula
//        // You need to determine the expected result manually or using external calculations
//
//        // Example expected value for illustration purposes (not accurate)
//        BigDecimal expectedPercentileRank = new BigDecimal("33.333");
//
//        assertEquals(expectedPercentileRank, card.getPercentileRank());
//    }
//}
