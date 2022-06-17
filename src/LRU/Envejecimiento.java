package LRU;

import Memoria.MarcoPagina;
import Memoria.MemoriaReal;

public class Envejecimiento extends Thread{

	/**
	 * Arreglo que representa la referencia a las páginas en cada ciclo
	 */
	private Integer[] bitsPaginas;

	/**
	 * Tabla de páginas con todas las páginas
	 */
	private MemoriaReal marcosPaginaMemoria;

	public Envejecimiento( Integer[] bitsPaginas, MemoriaReal pMarcoPagina) {
		this.bitsPaginas = bitsPaginas;
		this.marcosPaginaMemoria = pMarcoPagina;
	}

	public void actualizar() {

		synchronized (marcosPaginaMemoria) {
			MarcoPagina[] marcosPagina = marcosPaginaMemoria.darMarcosPagina();
			for (int i = 0; i < bitsPaginas.length; i++) {
				MarcoPagina mPagina = marcosPagina[i]; 				
				if(marcosPagina[i] != null)
				{
					if(bitsPaginas[i] == 1)						
					{
						mPagina.sumar();
						bitsPaginas[i] = 0;

					} else {
						mPagina.actualizar();
					}
				}					
			}			
		}

	}

	public void run() {
		while(marcosPaginaMemoria.darTerminar() != 5) {
			try {
				actualizar();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
