import java.time.LocalDate;
import java.util.*;

public class Biblioteca2025 {

	private final ArrayList <Libro> libros;
	private final ArrayList <Usuario> usuarios;
	private final ArrayList <Prestamo> prestamos;
	private final ArrayList <Prestamo> prestamosHist;

	public Biblioteca2025() {
		this.libros = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.prestamos = new ArrayList<>();
		this.prestamosHist = new ArrayList<>();
	}

	public static void main(String[] args) {
		Biblioteca2025 b= new Biblioteca2025();
		b.cargaDatos();
		b.fueraPlazo();
		b.menu();
	}

	public void menu(){
		Scanner sc=new Scanner (System.in);
		int opcion;
		do{
			options(true, new String[] {"LIBROS", "USUARIOS", "PRESTAMOS", "", "", "", "", "", "SALIR"});
			opcion=sc.nextInt();
			switch (opcion){
				case 1:{
					menuLibros();
					break;
				}
				case 2:{
					menuUsuarios();
					break;
				}
				case 3:{
					menuPrestamos();
					break;
				}
			}
		}while (opcion != 9);
	}

	private void menuLibros() {
		Scanner sc=new Scanner (System.in);
		int opcion;
		do{
			System.out.println("\n\nLIBROS\n");
			options(true, new String[] {"NUEVO LIBRO", "ELIMINAR LIBRO", "MODIFICAR LIBRO", "LISTADOS", "", "", "", "", "SALIR"});
			opcion=sc.nextInt();
			switch (opcion){
				case 1:{
					nuevoLibro();
					break;
				}
				case 2:{
					eliminarLibro();
					break;
				}
				case 3:{
					modificarLibros();
					break;
				}
				case 4:{
					listaLibros();
					break;
				}
			}
		}while (opcion != 9);
	}

	private void menuUsuarios() {
		Scanner sc=new Scanner (System.in);
		int opcion;
		do{
			System.out.println("\n\nUSUARIOS\n");
			options(true, new String[] {"NUEVO USUARIO", "ELIMINAR USUARIO", "MODIFICAR USUARIO", "LISTADOS", "", "", "", "", "SALIR"});
			opcion=sc.nextInt();
			switch (opcion){
				case 1:{
					nuevoUsuario();
					break;
				}
				case 2:{
					eliminarUsuario();
					break;
				}
				case 3:{
					modificarUsuario();
					break;
				}
				case 4:{
					listaUsuarios();
					break;
				}
			}
		}while (opcion != 9);
	}

	private void menuPrestamos() {
		Scanner sc=new Scanner (System.in);
		int opcion;
		do{
			System.out.println("\n\nPRESTAMOS\n");
			options(true, new String[] {"NUEVO PRESTAMO", "DEVOLUCION PRESTAMO", "PRORROGAR PRESTAMO", "LISTADO GENERAL PRESTAMOS", "LISTADO PRESTAMOS USUARIO", "LISTADO PRESTAMOS LIBRO (USUARIOS QUE LO LEEN)", "LIBRO MAS LEIDO", "USUARIO MAS LECTOR", "SALIR"});
			opcion=sc.nextInt();
			switch (opcion){
				case 1:{
					nuevoPrestamo();
					break;
				}
				case 2:{
					devolucion();
					break;
				}
				case 3:{
					prorroga();
					break;
				}
				case 4:{
					listaPrestamos();
					break;
				}
				case 5:{
					listaPrestamosUsu();
					break;
				}
				case 6:{
					listaPrestamosLibro();
					break;
				}
				case 7:{
					libroMasLeido();
					break;
				}
				case 8:{
					usuarioMasLector();
					break;
				}
			}
		}while (opcion != 9);
	}

	private void nuevoLibro() {
		Scanner sc = new Scanner(System.in);
		String isbn;
		do {
			isbn = solicitaIsbn();
		} while (!isbn.matches("\\d+-\\d+"));
		System.out.println("Teclea el titulo del libro:");
		String titulo = sc.nextLine();
		System.out.println("Teclea el autor del libro:");
		String autor = sc.nextLine();
		System.out.println("Teclea el genero del libro:");
		String genero = sc.nextLine();
		System.out.println("Teclea el numero de ejemplares del libro:");
		int ejemplares = sc.nextInt();
		libros.add(new Libro(isbn, titulo, autor, genero, ejemplares));
	}

	private void eliminarLibro() {
		System.out.println("Que libro deseas eliminar");
		listaLibros();
		int pos = buscaIsbn(solicitaIsbn());
		if (pos == -1) {
			System.out.println("No se encuentra ese libro");
		} else {
			libros.remove(pos);
			System.out.println("Libro eliminado");
		}
	}

	private void modificarLibros() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Que libro deseas modificar");
		listaLibros();
		int pos = buscaIsbn(solicitaIsbn());
		if (pos == -1) {
			System.out.println("No se encuentra ese libro");
		} else {
			System.out.println("Libro encontrado:");
			Libro l = libros.get(pos);
			System.out.println(l);
			System.out.println();
			System.out.println("Teclea el isbn del libro:");
			String isbn = sc.nextLine();
			if (isbn.isBlank()) {
				isbn = l.getIsbn();
			}
			l.setIsbn(isbn);
			System.out.println("Teclea el titulo del libro:");
			String titulo = sc.nextLine();
			if (titulo.isBlank()) {
				titulo = l.getTitulo();
			}
			l.setTitulo(titulo);
			System.out.println("Teclea el autor del libro:");
			String autor = sc.nextLine();
			if (autor.isBlank()) {
				autor = l.getAutor();
			}
			l.setAutor(autor);
			System.out.println("Teclea el genero del libro:");
			String genero = sc.nextLine();
			if (genero.isBlank()) {
				genero = l.getGenero();
			}
			l.setGenero(genero);
			System.out.println("Teclea numero de ejemplares del libro:");
			String ejemplares = sc.next();
			if (ejemplares.isBlank()) {
				ejemplares = String.valueOf(l.getEjemplares());
			}
			l.setEjemplares(Integer.parseInt(ejemplares));
			libros.set(pos, l);
		}
	}

	private void listaLibros() {
		for (Libro l : libros) {
			System.out.println(l);
		}
	}

	private void nuevoUsuario() {
		Scanner sc = new Scanner(System.in);
		String dni;
		do {
			dni = solicitaDni();
		} while (!dni.matches("\\d{2}"));
		System.out.println("Teclea el nombre del usuario:");
		String nombre = sc.nextLine();
		System.out.println("Teclea el email del usuario:");
		String email = sc.nextLine();
		String tlfn;
		do {
			System.out.println("Teclea el numero de telefono del usuario:");
			tlfn = sc.next();
		} while (!tlfn.matches("\\d{9}"));
		usuarios.add(new Usuario(dni, nombre, email, tlfn));
	}

	private void eliminarUsuario() {
		System.out.println("Que usuario deseas eliminar");
		listaUsuarios();
		int pos = buscaDni(solicitaDni());
		if (pos == -1) {
			System.out.println("No se encuentra ese usuario");
		} else {
			usuarios.remove(pos);
			System.out.println("Usuario eliminado");
		}
	}

	private void modificarUsuario() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Que usuario deseas modificar");
		listaUsuarios();
		int pos = buscaDni(solicitaDni());
		if (pos == -1) {
			System.out.println("No se encuentra ese usuario");
		} else {
			System.out.println("Usuario encontrado:");
			Usuario u = usuarios.get(pos);
			System.out.println(u);
			System.out.println();
			System.out.println("Teclea el dni:");
			String dni = sc.nextLine();
			if (dni.isBlank()) {
				dni = u.getDni();
			}
			u.setDni(dni);
			System.out.println("Teclea el nombre:");
			String nombre = sc.nextLine();
			if (nombre.isBlank()) {
				nombre = u.getNombre();
			}
			u.setNombre(nombre);
			System.out.println("Teclea el email:");
			String mail = sc.nextLine();
			if (mail.isBlank()) {
				mail = u.getEmail();
			}
			u.setEmail(mail);
			System.out.println("Teclea el telefono:");
			String tlfn = sc.nextLine();
			if (tlfn.isBlank()) {
				tlfn = u.getTelefono();
			}
			u.setTelefono(tlfn);
			usuarios.set(pos, u);
		}
	}

	private void listaUsuarios() {
		for (Usuario u : usuarios) {
			System.out.println(u);
		}
	}

	private void nuevoPrestamo() {
		System.out.println("Identificacion del usuario:");
		String dni=solicitaDni();
		int posUsuario = buscaDni(dni);

		if (posUsuario==-1){
			System.out.println("No es aun usuario de la biblioteca");
		}else{
			System.out.println("Identificacion del libro:");
			String isbn=solicitaIsbn();
			int posLibro=buscaIsbn(isbn);

			if (posLibro==-1){
				System.out.println("El ISBN pertenece a un libro inexistente");
			} else if (libros.get(posLibro).getEjemplares()>0){
				if ((buscaPrestamo(dni,isbn))==-1){
					LocalDate hoy=LocalDate.now();
					prestamos.add(new Prestamo(libros.get(posLibro),usuarios.get(posUsuario),hoy,hoy.plusDays(15)));
					libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares()-1);
				}else{
					System.out.println("Ese usuario ya tiene ese mismo libro en prestamo");
				}
			}else{
				System.out.println("No quedan unidades disponibles del libro");
			}
		}
	}

	private void devolucion() {
		System.out.println("Datos para la prorroga del préstamo:");
		String isbnLibro=solicitaIsbn();
		int pos=buscaPrestamo(solicitaDni(),isbnLibro);
		if (pos==-1){
			System.out.println("No hay ningun préstamo con esos datos");
		}else{
			prestamos.get(pos).setFechaDev(LocalDate.now());
			libros.get(buscaIsbn(isbnLibro))
					.setEjemplares(libros.get(buscaIsbn(isbnLibro)).getEjemplares()+1);
			prestamosHist.add(prestamos.get(pos));
			prestamos.remove(pos);
		}
	}

	private void prorroga() {
		System.out.println("Datos para la prorroga del préstamo:");
		String dni = solicitaDni();
		String isbn = solicitaIsbn();
		int pos=buscaPrestamo(dni,isbn);
		if (pos==-1){
			System.out.println("No hay ningun préstamo con esos datos");
		}else{
			prestamos.get(pos).setFechaDev(prestamos.get(pos).getFechaDev().plusDays(15));
			prestamos.get(pos).setFechaPrest(LocalDate.now());
		}
	}

	private void listaPrestamos() {
		System.out.println("Listado de prestamos activos");
		for (Prestamo p : prestamos) {
			if (p.getFechaDev().isBefore(LocalDate.now())){
				System.out.print("Libro fuera de plazo: ");
			}
			System.out.println(p);
		}

		System.out.println("\nListado de prestamos historicos");
		for (Prestamo p : prestamosHist) {
			System.out.println(p);
		}
	}

	private void listaPrestamosUsu(){
		String dni=solicitaDni();
		int pos=buscaDni(dni);

		if (pos==-1){
			System.out.println("No tengo a nadie con ese DNI");
		}else{
			System.out.println("Prestamos activos de: "
					+ usuarios.get(pos).getNombre());
			for (Prestamo p : prestamos) {
				if (p.getUsuarioPrest().getDni().equals(dni)){
					if (p.getFechaDev().isBefore(LocalDate.now())){
						System.out.print("Libro fuera de plazo: ");
					}
					System.out.println(p);
				}
			}
			System.out.println("\nPrestamos ya devueltos por: "
					+ usuarios.get(pos).getNombre());
			for (Prestamo p : prestamosHist) {
				if (p.getUsuarioPrest().getDni().equals(dni)){
					System.out.println(p);
				}
			}
		}
	}

	private void listaPrestamosLibro(){
		String isbn=solicitaIsbn();
		int pos=buscaIsbn(isbn);
		if (pos==-1){
			System.out.println("No tengo ningún libro con ese ISBN");
		}else{
			System.out.println("Usuarios/as que lo estan leyendo");
			for (Prestamo p : prestamos) {
				if (p.getLibroPrest().getIsbn().equals(isbn)){
					System.out.println(p.getUsuarioPrest());
				}
			}

			System.out.println("Usuarios/as que ya lo han leido");
			for (Prestamo p : prestamosHist) {
				if (p.getLibroPrest().getIsbn().equals(isbn)){
					System.out.println(p.getUsuarioPrest());
				}
			}
		}
	}

	public void fueraPlazo(){
		System.out.println("Prestamos fuera de plazo:");
		for (Prestamo p : prestamos) {
			if (p.getFechaDev().isBefore(LocalDate.now())){
				System.out.println(p);
			}
		}
	}

	public void libroMasLeido(){
		HashMap<Libro, Integer> usosLibros = new HashMap<>();
		for (Prestamo p : prestamos) {
			usosLibros.put(p.getLibroPrest(), usosLibros.getOrDefault(p.getLibroPrest(), 0)+1);
		}
		for (Prestamo p : prestamosHist) {
			usosLibros.put(p.getLibroPrest(), usosLibros.getOrDefault(p.getLibroPrest(), 0)+1);
		}
		ArrayList<Integer> usos = new ArrayList<>(usosLibros.values());

		if (usos.stream().sorted().dropWhile(v -> v != Collections.max(usos)).count()>1){
			System.out.println("Los libros mas leidos, con " +Collections.max(usos)+ " prestamos son:");
			for (Map.Entry<Libro, Integer> entry : usosLibros.entrySet()) {
				if (Objects.equals(entry.getValue(), Collections.max(usos))){
					System.out.println(entry.getKey());
				}
			}
		} else {
			System.out.println("El libro mas leido es: "+libros.get(usos.indexOf(Collections.max(usos)))+" con "+Collections.max(usos)+" prestamos");
		}
	}

	public void usuarioMasLector(){
		HashMap<Usuario, Integer> leidos = new HashMap<>();
		for (Prestamo p : prestamos) {
			leidos.put(p.getUsuarioPrest(), leidos.getOrDefault(p.getUsuarioPrest(), 0)+1);
		}
		for (Prestamo p : prestamosHist) {
			leidos.put(p.getUsuarioPrest(), leidos.getOrDefault(p.getUsuarioPrest(), 0)+1);
		}
		ArrayList<Integer> usos = new ArrayList<>(leidos.values());

		if (usos.stream().sorted().dropWhile(v -> v != Collections.max(usos)).count()>1){
			System.out.println("Los usuarios mas lectores, con " +Collections.max(usos)+ " libros leidos son:");
			for (Map.Entry<Usuario, Integer> entry : leidos.entrySet()) {
				if (entry.getValue().equals(Collections.max(usos))){
					System.out.println(entry.getKey());
				}
			}
		} else {
			System.out.println("El usuario mas lector es: "+libros.get(usos.indexOf(Collections.max(usos)))+" con "+Collections.max(usos)+" libros leidos");
		}
	}

	public String solicitaDni(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Teclea el dni del usuario:");
		return sc.next();
	}

	/**
	 * Método para solicitar por teclado el ISBN de un libro. pdte de validación
	 * @return (String) isbn del libro tecleado
	 */
	public String solicitaIsbn(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Teclea el isbn del libro:");
		return sc.next();
	}

	/**
	 * Método para buscar un dni en la colección usuarios
	 * @param dni (String) del usuario a buscar en la colección
	 * @return posición (int) del usuario en el Arraylist, valor -1 si no se encuentra
	 */
	public int buscaDni(String dni){
		int pos=-1;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getDni().equals(dni)){
				pos=i;
				break;
			}
		}
		return pos;
	}

	/**
	 * Método para buscar un libro en la colección libros
	 * @param isbn (String) del libro a buscar en la colección
	 * @return posición (int) del libro el Arraylist, valor -1 si no se encuentra
	 */
	public int buscaIsbn(String isbn){
		int pos=-1;
		for (int i = 0; i < libros.size(); i++) {
			if (libros.get(i).getIsbn().equals(isbn)){
				pos=i;
				break;
			}
		}
		return pos;
	}
	/**
	 * Método para buscar un préstamo en la colección préstamos
	 * @param dni (String) del usuario que realizó el préstamo
	 * @param isbn (String) del libro prestado
	 * @return posición (int) del préstamo en el Arraylist,
	 *         valor -1 si no se encuentra un préstamo con esos datos
	 */
	public int buscaPrestamo(String dni, String isbn){
		int pos=-1;
		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.get(i).getUsuarioPrest().getDni().equals(dni)
					&& prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)){
				pos=i;
				break;
			}
		}
		return pos;
	}

	public void cargaDatos(){

		libros.add(new Libro("1-11","El Hobbit","JRR Tolkien","Aventuras",3));
		libros.add(new Libro("1-22","El Silmarillon","JRR Tolkien","Aventuras",3));
		libros.add(new Libro("1-33","El Medico","N. Gordon","Aventuras",4));
		libros.add(new Libro("1-44","Chaman","N. Gordon","Aventuras",3));
		libros.add(new Libro("1-55","Momo","M. Ende","Aventuras",2));
		libros.add(new Libro("1-66","Paraiso inhabitado","A.M.Matute","Aventuras",2));
		libros.add(new Libro("1-77","Olvidado Rey Gudu","A.M.Matute","Aventuras",2));
		libros.add(new Libro("1-88","El ultimo barco","D.Villar","Novela Negra",3));
		libros.add(new Libro("1-99","Ojos de agua","D.Villar","Novela Negra",9
		));

		usuarios.add(new Usuario("11","Ana","ana@email.com","621111111"));
		usuarios.add(new Usuario("22","David","david@email.com","622222222"));
		usuarios.add(new Usuario("33","Bea","bea@email.com","623333333"));
		usuarios.add(new Usuario("44","Lucas","lucas@email.com","624444444"));
		usuarios.add(new Usuario("55","Carlota","carlota@email.com","625555555"));
		usuarios.add(new Usuario("66","Juan","juan@email.com","626666666"));

		LocalDate hoy= LocalDate.now();
		prestamos.add(new Prestamo(libros.get(0),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
		prestamos.add(new Prestamo(libros.get(0),usuarios.get(0), hoy,hoy.plusDays(15)));
		prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy,hoy.plusDays(15)));
		prestamos.add(new Prestamo(libros.get(5),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
		prestamos.add(new Prestamo(libros.get(1),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
		prestamos.add(new Prestamo(libros.get(2),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
		prestamos.add(new Prestamo(libros.get(3),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
		prestamos.add(new Prestamo(libros.get(6),usuarios.get(4), hoy,hoy.plusDays(15)));
		prestamos.add(new Prestamo(libros.get(6),usuarios.get(1), hoy,hoy.plusDays(15)));

		prestamosHist.add(new Prestamo(libros.get(0),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(2),usuarios.get(0), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(7),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(5),usuarios.get(4), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(1),usuarios.get(1), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(7),usuarios.get(2), hoy.minusDays(20),hoy.minusDays(5)));
		prestamosHist.add(new Prestamo(libros.get(6),usuarios.get(3), hoy.minusDays(20),hoy.minusDays(5)));
	}

	public static void options(boolean input,String[] a) {
		int maxValue = 0;
		for (int i = 0; i < a.length; i++) {
			maxValue = Math.max(maxValue, String.valueOf(a.length).length() - String.valueOf(i).length() + ((input ? i + ". " : "") + a[i]).length());

		}
		System.out.print('╔');
		for (int x = 0; x < maxValue + 2; x++) {
			System.out.print('═');
		}
		System.out.println('╗');
		int contador = 1;
		for (String s : a) {
			System.out.print("║ ");
			int espacios = String.valueOf(a.length).length() - String.valueOf(contador).length();
			for (int x = 0; x < espacios; x++) {
				System.out.print(" ");
			}
			System.out.print(input ? contador + ". " : "");
			System.out.print(s);
			for (int y = 0; y < 1 + maxValue - ((input ? espacios : 0) + (input ? contador + ". " + s : s).length()); y++) {
				System.out.print(' ');
			}
			System.out.println("║");
			contador++;
		}
		System.out.print('╚');
		for (int x = 0; x < maxValue + 2; x++) {
			System.out.print('═');
		}
		System.out.println('╝');
		System.out.print(input ? ">> " : "");
	}
}