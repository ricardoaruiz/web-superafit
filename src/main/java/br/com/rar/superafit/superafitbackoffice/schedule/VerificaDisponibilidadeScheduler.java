package br.com.rar.superafit.superafitbackoffice.schedule;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.rar.superafit.superafitbackoffice.webservice.WebServiceClient;
import retrofit2.Call;
import retrofit2.Response;

@Component
public class VerificaDisponibilidadeScheduler {

	private final Logger LOG = LoggerFactory.getLogger(VerificaDisponibilidadeScheduler.class);

	@Value("${enable.verify.available.resources}")
	private String enableVerifyAvailableResources;
	
	@Autowired
	private WebServiceClient webServiceClient;
	
	@Scheduled(fixedRate=6000)
	public void verificaDisponibilidade() {
		if (isEnableVerifyAvailableResouces()) {
			try {
				LOG.info("Obtendo os dados de treino da fonte de dados para verificação...");
				Call<Void> call = webServiceClient.getKeepAliveApiWebService().isOn();
				Response<Void> response = call.execute();
				LOG.info("Http code: " + response.code());
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	private boolean isEnableVerifyAvailableResouces() {
		return Boolean.valueOf(enableVerifyAvailableResources);
	}
	
}
