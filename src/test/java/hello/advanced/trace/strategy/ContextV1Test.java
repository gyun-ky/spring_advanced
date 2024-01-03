package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {

	@Test
	void strategyV0() {
		logic1();
		logic2();
	}

	@Test
	void strategyV1() {
		StrategyLogic1 strategy1 = new StrategyLogic1();
		log.info("strategyLogic1={}", strategy1.getClass());
		ContextV1 contextV1 = new ContextV1(strategy1);
		contextV1.execute();

		StrategyLogic2 strategy2 = new StrategyLogic2();
		ContextV1 contextV2 = new ContextV1(strategy2);
		contextV2.execute();
	}

	// 익명 내부 클래스 사용
	@Test
	void strategyV2() {
		Strategy strategy1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		log.info("strategyLogic1={}", strategy1.getClass());
		ContextV1 contextV1 = new ContextV1(strategy1);
		contextV1.execute();

		Strategy strategy2 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		};
		log.info("strategyLogic2={}", strategy2.getClass());
		ContextV1 contextV2 = new ContextV1(strategy2);
		contextV2.execute();

	}

	@Test
	void strategyV3() {
		ContextV1 contextV1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});
		contextV1.execute();

		ContextV1 contextV2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
		contextV1.execute();
	}

	@Test
	void strategyV4() {
		ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		contextV1.execute();

		ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		contextV2.execute();
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행
		log.info("비즈니스 로직1 실행");

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	private void logic2(){
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행
		log.info("비즈니스 로직2 실행");

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
}
