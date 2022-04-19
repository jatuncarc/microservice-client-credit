package bootcamp.microservices.app.clientcredit.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.microservices.app.clientcredit.documents.ClientCredit;
import bootcamp.microservices.app.clientcredit.services.ClientCreditService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientCreditController {

	@Autowired
	private ClientCreditService clientCreditService;

	@PostMapping
	public Mono<ClientCredit> createClient(@Valid @RequestBody ClientCredit clientCredit) {
		return clientCreditService.save(clientCredit);
	}

	@GetMapping("/all")
	public Flux<ClientCredit> searchAll() {
		return clientCreditService.findAll();
	}

	@GetMapping("/id/{id}")
	public Mono<ClientCredit> searchById(@PathVariable String id) {
		return clientCreditService.findById(id);
	}

	@PutMapping
	public Mono<ClientCredit> updateClientCredit(@RequestBody ClientCredit clientCredit) {
		return clientCreditService.update(clientCredit);
	}
	
	@DeleteMapping
	public Mono<ClientCredit> deleteClientCredit(@Valid @RequestBody ClientCredit clientCredit) {
		return clientCreditService.deleteLogic(clientCredit);
	}

}
