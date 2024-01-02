package hello.advanced.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

	private final OrderServiceV4 orderService;
	private final LogTrace logTrace;

	@GetMapping("/v4/request")
	public String request(String itemId) {

		AbstractTemplate template = new AbstractTemplate<>(logTrace) {
			@Override
			protected Object call() {
				orderService.orderItem(itemId);
				return "ok";
			}
		};
		return (String) template.execute("OrderController.request()");
	}
}
