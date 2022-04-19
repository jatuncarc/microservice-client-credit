package bootcamp.microservices.app.clientcredit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import bootcamp.microservices.app.clientcredit.documents.Client;
import bootcamp.microservices.app.clientcredit.documents.ClientCredit;
import reactor.core.publisher.Mono;

public interface ClientCreditRepository extends ReactiveMongoRepository<ClientCredit, String> {

	public Mono<ClientCredit> findByClient(Client client);
}
