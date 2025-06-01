package kafka;

import java.util.List;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaReceiver {

	private static final Logger log = Logger.getLogger(KafkaReceiver.class.getName());

	private final String topic = "content";

	public KafkaReceiver() {
		KafkaUtils.createTopic(topic); // Create topic if not exists

		KafkaSubscriber subscriber = KafkaSubscriber.createSubscriber(
			"localhost:9092,kafka:9092", List.of(topic)
		);

		subscriber.start(new RecordProcessor() {
			@Override
			public void onReceive(ConsumerRecord<String, String> r) {
				log.info(r.topic() + " , " + r.offset() + " -> " + r.value());
			}
		});
	}

	// Optionally, you can add a `main` to run it
	public static void main(String[] args) {
		new KafkaReceiver();
	}
}
