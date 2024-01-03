package hello.advanced.app.v5;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate template;

	public OrderRepositoryV5(LogTrace logTrace) {
		this.template = new TraceTemplate(logTrace);
	}

	public void save(String itemId) {

		template.execute("OrderRepository.save()", new TraceCallback<Object>() {
			@Override
			public Object call() {
				if(itemId.equals("ex")){
					throw new IllegalStateException("예외 발생!");
				}
				sleep(1000);
				return null;
			}
		});

	}

	private void sleep(int millis) {
		try{
			Thread.sleep(millis);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
