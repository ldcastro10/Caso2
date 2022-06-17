package Memoria;

public class Pagina {
		
	private int refMarcoPagina;
	
	public Pagina() {
		this.refMarcoPagina = -1;
	}
	
	public int darRefMarcoPagina() {
		return refMarcoPagina;
	}
	
	public void actualizarRefMarcoPagina(int pMarcoPagina) {
		this.refMarcoPagina = pMarcoPagina;
	}	
}
