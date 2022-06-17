package Memoria;

public class MemoriaReal {

	private MarcoPagina[] marcosPagina;

	private int terminar;

	public MemoriaReal(int pMarcoPagina) {
		this.terminar = 0;
		this.marcosPagina = new MarcoPagina[pMarcoPagina];
	}

	public MarcoPagina[] darMarcosPagina() {
		return marcosPagina;
	}
	public int falloPagina() {
		int menor = Integer.MAX_VALUE;
		int posicion = -1;
		MarcoPagina nuevo = new MarcoPagina();

		for (int i = 0; i < marcosPagina.length && menor != -1; i++) {
			if( marcosPagina[i] != null ) {
				MarcoPagina actual = marcosPagina[i];
				if( actual.darContador() < menor) {
					posicion = i;
					menor = actual.darContador();
				}
			} else {
				posicion = i;
				menor = -1;
			}
		} 
		marcosPagina[posicion] = nuevo;
		return posicion;		
	}

	public void cambiarTerminar() {
		terminar = 5;
	}

	public int darTerminar() {
		return terminar;
	}
}
