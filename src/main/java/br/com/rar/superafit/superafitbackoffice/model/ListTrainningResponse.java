package br.com.rar.superafit.superafitbackoffice.model;

import java.io.Serializable;
import java.util.List;

public class ListTrainningResponse implements Serializable {

	private static final long serialVersionUID = -7886971407319567359L;
	
	private List<TrainningResponse> data;
	
	private boolean sync;

	public List<TrainningResponse> getData() {
		return data;
	}

	public void setData(List<TrainningResponse> data) {
		this.data = data;
	}

	public boolean isSync() {
		return sync;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}
	
}
