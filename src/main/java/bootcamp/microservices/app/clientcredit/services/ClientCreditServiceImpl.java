package bootcamp.microservices.app.clientcredit.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.microservices.app.clientcredit.documents.ClientCredit;
import bootcamp.microservices.app.clientcredit.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.clientcredit.repository.ClientCreditRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientCreditServiceImpl implements ClientCreditService {

	private static final Logger log = LoggerFactory.getLogger(ClientCreditServiceImpl.class);

	@Autowired
	private ClientCreditRepository clientCreditRepository;

	@Override
	public Flux<ClientCredit> findAll() {
		return clientCreditRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Clients not exist")));
	}

	@Override
	public Mono<ClientCredit> findById(String id) {
		return clientCreditRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("ClientCredit not found")));
	}

	@Override
	public Mono<ClientCredit> update(ClientCredit clientCredit) {
		return clientCreditRepository.findById(clientCredit.getId()).flatMap(c -> {
			clientCredit.setModifyUser(clientCredit.getModifyUser());
			clientCredit.setModifyDate(new Date());
			return clientCreditRepository.save(clientCredit);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("ClientCredit not found")));
	}

	@Override
	public Mono<ClientCredit> save(ClientCredit clientCredit) {
		return clientCreditRepository.findByClient(clientCredit.getClient())
				.switchIfEmpty(clientCreditRepository.save(clientCredit));
	}

	@Override
	public Mono<Void> deleteNonLogic(ClientCredit clientCredit) {
		return clientCreditRepository.findById(clientCredit.getId()).flatMap(c -> {
			return clientCreditRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("ClientCredit not found")));
	}

	@Override
	public Mono<ClientCredit> deleteLogic(ClientCredit clientCredit) {
		return clientCreditRepository.findById(clientCredit.getId()).flatMap(c -> {
			c.setModifyUser(clientCredit.getModifyUser());
			c.setModifyDate(new Date());
			return clientCreditRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("ClientCredit not found")));
	}

}
