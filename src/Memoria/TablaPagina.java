package Memoria;


public class TablaPagina {

	private Pagina[] tablaPaginas;

	public TablaPagina(int paginas ) {

		this.tablaPaginas = new Pagina[paginas];
		iniciarlizarPaginas();
	}
	
	private void iniciarlizarPaginas() {
		for (int i = 0; i < tablaPaginas.length; i++) {
			Pagina p = new Pagina();
			tablaPaginas[i] = p;
		}
	}
	
	public void relacionar(int posTablaPagina, int posMarcoPagina) {		
		
		for (int i = 0; i < tablaPaginas.length; i++) {
			if(tablaPaginas[i].darRefMarcoPagina() == posMarcoPagina) {
				tablaPaginas[i].actualizarRefMarcoPagina(-1);
			}
		}		
		tablaPaginas[posTablaPagina].actualizarRefMarcoPagina(posMarcoPagina);
	}
	
	/**
	 * Posici�n del marco de p�gina asociado con la p�gina
	 * @param posTablaPaginas Posici�n en la tabla de p�ginas a revisas
	 * @return Posici�n del marco de p�gina asociado o -1 si la p�gina no est� asociada a un marco de p�gina
	 */
	public int obtenerRefMarcoPagina(int posTablaPaginas) {
		int ubicacionMarcoPagina = tablaPaginas[posTablaPaginas].darRefMarcoPagina();	
		return ubicacionMarcoPagina;
	}
	
	public String imprimir() {
		for (int i = 0; i < tablaPaginas.length; i++) {
			System.out.println( "\t" + " " + i + " " + tablaPaginas[i].darRefMarcoPagina());
		}
		return "";
	}
}
