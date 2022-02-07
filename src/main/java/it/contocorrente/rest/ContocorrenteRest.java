package it.contocorrente.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import it.contocorrente.entity.Contocorrente;
import it.contocorrente.entity.Movimento;
import it.contocorrente.entity.Operazione;

@Path ("/conto")
public class ContocorrenteRest {

	private static ArrayList<Contocorrente> conti = new ArrayList<>();
	private static ArrayList<Movimento> movimenti = new ArrayList<>();

	@POST
	@Path("/conto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creaConto(Contocorrente c) {
		for(Contocorrente con : conti)
			if(c.getIban().equals(con.getIban())) {
				return Response.status(404).entity("Il conto è già esistente!").build();
			}
		conti.add(c);
		return Response.status(200).entity ("Conto corrente creato correttamente!").build();
	}	

	@DELETE
	@Path("/conto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response eliminaConto(Contocorrente c) {
		for(Contocorrente con : conti) {
			if(con.getIban().equals(c.getIban())) {
				conti.remove(con);
				return Response.status(200).entity ("Il conto corrente è stato rimosso!").build();
			}
		}
		return Response.status(404).entity("Il conto inserito non esiste!").build();
	}
	
	@PUT
	@Path("/conto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response aggiornaConto(Contocorrente c) {
		for(Contocorrente con : conti) {
			if(con.getIban().equals(c.getIban())) {
				int index = conti.lastIndexOf(con);
				conti.set(index, c);
				return Response.status(200).entity("Il conto è stato aggiornato!").build();	
			}
		}
		return Response.status(404).entity("Il conto non è stato aggiornato!").build();
	}

	@PUT
	@Path("/{movimento}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movimento(Movimento m) {
		for (Contocorrente con : conti) {
			if(con.getIban().equals(m.getIban())) {
				if (m.getTipo().equals(Operazione.PRELIEVO)) {
					if(m.getImporto() > con.getSaldo()) {
						return Response.status(406).entity("Movimento non riuscito! L'importo richiesto è maggiore del saldo!").build();
					}
					double nuovoSaldo = con.getSaldo()-m.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(m);
					return Response.status(200).entity("Movimento riuscito! Il saldo attuale è " + nuovoSaldo).build();	
				}
				else if (m.getTipo().equals(Operazione.VERSAMENTO)) {
					double nuovoSaldo = con.getSaldo()+m.getImporto();
					con.setSaldo(nuovoSaldo);
					movimenti.add(m);
					return Response.status(200).entity("Movimento riuscito! Il nuovo saldo è " + nuovoSaldo).build();
				}
			}
		}
		return Response.status(404).entity("L'operazione non è andata a buon fine!").build();
	}	

	@GET
	@Path("/tuttiimovimenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	@GET
	@Path("/tuttiiconti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contocorrente> getConti() {
		return conti;
	}
}