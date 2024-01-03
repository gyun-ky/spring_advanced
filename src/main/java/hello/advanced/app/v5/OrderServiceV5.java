package hello.advanced.app.v5;

import org.springframework.stereotype.Service;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace) {
		this.orderRepository = orderRepository;
		this.template = new TraceTemplate(logTrace);
	}

	public void orderItem(String itemId) {

		template.execute("OrderService.orderItem()", new TraceCallback<Object>() {
			@Override
			public Object call() {
				orderRepository.save(itemId);
				return null;
			}
		});

	}
}
