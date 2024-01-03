package hello.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

	// Context의 필드에 Strategy를 주입하여 사용하는 방법
	private Strategy strategy;

	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}

	public void execute() {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행
		strategy.call(); // 위임

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	// 단점
	// Context와 Strategy를 조립한 이후에는 전략을 변경하기 번거롭다
	// Context를 싱글톤으로 사용할 경우, 동시성 이슈등 고려할 사항이 많다.
}
