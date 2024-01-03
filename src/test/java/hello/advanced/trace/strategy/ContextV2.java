package hello.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

	// 템플릿 콜백 패턴
	// 전랴을 파라미터로 받는 방식
	// Context - 템플릿, Strategy - 콜백
	// XXTemplate이면 콜백 패턴으로 생성되어있다고 생각

	public void execute(Strategy strategy) {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행
		strategy.call();

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
}
