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
	 * Posición del marco de página asociado con la página
	 * @param posTablaPaginas Posición en la tabla de páginas a revisas
	 * @return Posición del marco de página asociado o -1 si la página no está asociada a un marco de página
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
