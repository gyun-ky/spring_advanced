package hello.advanced.app.v3;

import org.springframework.stereotype.Service;

import hello.advanced.trace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepository;
	private final LogTrace logTrace;

	public void orderItem(String itemId) {

		TraceStatus status = null;
		try{
			status = logTrace.begin("OrderService.orderItem()");
			orderRepository.save(itemId);
			logTrace.end(status);
		}catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
