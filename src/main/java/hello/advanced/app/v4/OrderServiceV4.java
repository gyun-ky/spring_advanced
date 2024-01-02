package hello.advanced.app.v4;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;
	private final LogTrace logTrace;

	public void orderItem(String itemId) {

		AbstractTemplate template = new AbstractTemplate<>(logTrace) {
			@Override
			protected Object call() {
				orderRepository.save(itemId);
				return null;
			}
		};

		template.execute("OrderService.orderItem()");


	}
}
