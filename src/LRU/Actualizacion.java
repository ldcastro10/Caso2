package LRU;

import java.util.ArrayList;

import Memoria.MemoriaReal;
import Memoria.TablaPagina;

public class Actualizacion extends Thread{

	/**
	 * Referencias a realizar en la ejecución del proceso
	 */
	private ArrayList<Integer> referencias = new ArrayList<Integer>();

	/**
	 * Marcos de página del proceso
	 */
	private MemoriaReal memoriaReal;

	/**
	 * Tablas de página del proceso
	 */
	private TablaPagina tablaPagina;

	/**
	 * Referencia a las páginas referenciadas en el último intervalo
	 */
	private Integer[] bitsPaginas;

	/**
	 * Número total de fallos de página ocurridos
	 */
	private int fallosPagina;

	private boolean terminado;

	public Actualizacion( MemoriaReal pMarcoPagina, TablaPagina pTablaPagina, ArrayList<Integer> referencias, Integer[] pBitsPaginas ) {
		this.memoriaReal = pMarcoPagina;
		this.tablaPagina = pTablaPagina;
		this.referencias = referencias;
		this.bitsPaginas = pBitsPaginas;		
	}

	private void leerReferencia(int posTablaPagina, int i) {
		synchronized (memoriaReal) {
			int posMarcoPagina = tablaPagina.obtenerRefMarcoPagina(posTablaPagina);
			if( posMarcoPagina != -1) {			
				bitsPaginas[posMarcoPagina] = 1;
			} else {
				fallosPagina++;
				posMarcoPagina = memoriaReal.falloPagina();
				tablaPagina.relacionar(posTablaPagina, posMarcoPagina);
				bitsPaginas[posMarcoPagina] = 1;
			}
		}		
	}

	public void run() {
		int posTablaPagina;
		for (int i = 0; i < referencias.size(); i++) {				
			try {
				posTablaPagina = referencias.get(i);
				leerReferencia(posTablaPagina, i);
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		memoriaReal.cambiarTerminar();
		System.out.println("Número de Fallo de Páginas:" + fallosPagina);
	}
}
