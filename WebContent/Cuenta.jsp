<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Account"%>
<%@page import="dominio.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<%
	User userLogin = new User();
	userLogin = (User) session.getAttribute("userLogin");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Banco UTN - Alta cuenta</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MENÚ ACÁ -->
		<jsp:include page="MenuAdmin.html"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${userLogin.getFirstName()}
									${userLogin.getLastName()}</span> <img
								class="img-profile rounded-circle" src="img/profile.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="collapse-item" href="ServletLogin?btnLogout=1"><i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i><span>Cerrar
										sesi�n</span> </a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800"><%=request.getParameter("p")%>
							Cuenta
						</h1>
					</div>

					<form class="needs-validation" method="post"
						action="ServletsCuentas">
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="title">DNI</label> <input class="form-control"
									name="txtDNI" required="required" type="number" pattern="[0-9]+"
									placeholder="Ingrese DNI" value="${DNI}"> <input
									type="hidden" name="parameter"
									value="<%=request.getParameter("p")%>">
							</div>
							<div class="form-group col-md-1 mt-1">
								<button type="submit" name="BuscarExistencia"
									class="btn btn-warning btn-circle mt-4">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
						<%
							if (request.getAttribute("estadoExistencia") != null) {
								boolean estadoM = (Boolean) request.getAttribute("estadoExistencia");

								if (estadoM == true) {
									if (request.getParameter("p").equals("Alta")) {
						%>
						<label for="title">${Nombre}</label>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="title">CBU</label> <input class="form-control"
									maxlength="22" name="txtCBU" required="required" value="${CBU}" type="text" pattern="\d*"
									placeholder="N� identidad + N� Sucursal + D�gito identificador + Numero de cuenta + D�gito verificador">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Tipo de cuenta</label> <select
									class="custom-select" name="tipoCta" id="inputGroupSelect02">
									<option selected>Elija opci�n</option>
									<option value="1">Caja de ahorro</option>
									<option value="2">Cuenta corriente</option>
								</select>
							</div>

							<div class="form-group col-md-3">
								<label for="title">Saldo inicial ($ - pesos argentinos)</label>
								<input class="form-control" name="txtSaldo" type="text"
									value="10000" placeholder="Saldo inicial">
							</div>
						</div>
						<%
							} else {
						%>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Seleccione cuenta a dar de baja</label> <select
									class="custom-select" name="NroCuentaBaja"
									id="inputGroupSelect03">
									<option selected>Elija Cuenta</option>
									<%
										List<Account> listA = null;
													if (request.getAttribute("listaccount") != null) {
														listA = (List<Account>) request.getAttribute("listaccount");
													}
													if (listA != null)
														for (Account a : listA) {
									%>
									<option value="<%=a.getAccountNumber()%>">Nro:
										<%=a.getAccountNumber()%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>
						<%
							}
						%>
						<div class="form-row">
							<div class="form-group col-md-3">


								<div class="modal fade" id="modalAltaUsuario" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">
													Dar de
													<%=request.getParameter("p")%>
													cuenta?
												</h5>
												<button class="close" type="button" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">�</span>
												</button>
											</div>
											<div class="modal-body">
												Realmente quer�s dar de
												<%=request.getParameter("p")%>
												la cuenta?
											</div>
											<div class="modal-footer">
												<button type="submit" name="btnGestionarCuenta"
													class="btn btn-primary mt-2">
													Dar de
													<%=request.getParameter("p")%></button>
											</div>
										</div>
									</div>
								</div>
								<a class="btn btn-primary" href="#" data-toggle="modal"
									data-target="#modalAltaUsuario"> Dar de <%=request.getParameter("p")%>
								</a>

							</div>
						</div>

					</form>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->
			<%
				}
				}
				if (request.getAttribute("logearError") != null) {
			%><script>
				alert("${logearError}");
			</script>
			<%
				}
			%>
			<%
				if (request.getAttribute("DadaBaja") != null) {
			%><script>
				alert("Cuenta dada de Baja correctamente");
			</script>
			<%
				}
			%>
			<%
				if (request.getAttribute("DadaAlta") != null) {
			%><script>
				alert("Cuenta dada de Alta correctamente");
			</script>
			<%
				}
			%>
			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Banco UTN 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- End of Page Wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>
