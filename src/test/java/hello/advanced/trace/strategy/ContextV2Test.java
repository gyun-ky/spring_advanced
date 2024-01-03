package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2Test {

	@Test
	void strategyV1() {
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogic1());
		context.execute(new StrategyLogic2());
		// callBack을 넘겨준다 (코드가 call은 되는데, 코드를 넘겨준 곳의 뒤 back에서 실행된다는 의미)
		// 다른 코드의 인수로서 넘겨주는 실행가능한 코드
	}

	@Test
	void strategyV2() {
		ContextV2 context = new ContextV2();
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		});

		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
		});
	}

	@Test
	void strategyV3() {
		ContextV2 context = new ContextV2();
		context.execute(()-> {
			log.info("비즈니스 로직1 실행");
		});
		context.execute(()-> {
			log.info("비즈니스 로직1 실행");
		});
	}
}
