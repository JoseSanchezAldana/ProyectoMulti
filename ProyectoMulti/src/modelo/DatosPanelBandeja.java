package modelo;

public class DatosPanelBandeja {

	    private String parade;
	    private String asunto;

	    public DatosPanelBandeja(String columna1, String columna2) {
	        this.parade = columna1;
	        this.asunto = columna2;
	    }

	    public String getParade() {
	        return parade;
	    }

	    public String getAsunto() {
	        return asunto;
	    }

}
