package bootcamp.microservices.app.clientcredit.services;

import bootcamp.microservices.app.clientcredit.documents.ClientCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientCreditService {

	public Flux<ClientCredit> findAll();

	public Mono<ClientCredit> findById(String id);

	public Mono<ClientCredit> save(ClientCredit clientCredit);

	public Mono<ClientCredit> update(ClientCredit clientCredit);

	public Mono<Void> deleteNonLogic(ClientCredit clientCredit);

	public Mono<ClientCredit> deleteLogic(ClientCredit clientCredit);

}
