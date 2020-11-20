<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="dominio.User" %>
<%@page import="dominio.Nationality"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%
    User userLogin = new User(); 
    userLogin = (User)session.getAttribute("userLogin");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Banco UTN - Cliente</title>

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

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="MenuCliente.html"></jsp:include>
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
						<li class="nav-item dropdown no-arrow">
							<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<span class="mr-2 d-none d-lg-inline text-gray-600 small">${userLogin.getFirstName()} ${userLogin.getLastName()}</span> 
								<img src="https://img.icons8.com/color/48/000000/circled-user-male-skin-type-6.png" alt="" />
							</a> <!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="dropdown-item" href="ServletClientes?listarCliente=1">
									<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
								</a>
								<div class="dropdown-divider"></div>
								<a class="collapse-item" href="ServletLogin?btnLogout=1"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i><span>Cerrar sesión</span> </a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Navs -->
					<nav class="nav nav-borders">
                     	<a class="nav-link active ml-0" href="ServletClientes?listarCliente=1">Datos</a>
                    </nav>
					<!-- Content Row -->
					<div class="row">
						<div class="col-xl-1"> </div>
						<div class="col-xl-10">
							<!-- Account details card-->
							<div class="card mb-4">
								<div class="card-header">Detalles de cuenta</div>
								<form method="get" action="ServletClientes?dataUpdate=1">
								<div class="card-body">
										<h4><%=userLogin.getFirstName()%>  <%userLogin.getLastName();%></h4>
										
										<div class="form-row">
											<!-- Form Group (organization name)-->
											<div class="form-group col-md-6">
												<label class="small mb-1" for="user">Tu usuario</label> 
												<input class="form-control" id="user" type="text" name= "readUser" value="<%=userLogin.getUserName() %>" readonly>
											</div>
										</div>
										
										<div class="form-row">
											<div class="form-group col-md-6">
												<label class="small mb-1" for="password">Tu contraseña</label>
												<input class="form-control" id="password" type="password" name= "readPassword" value="">
											</div>
											<div class="form-group col-md-6">
												<label class="small mb-1" for="password">Repetí tu contraseña</label>
												<input class="form-control" id="password" type="password" name= "repeatReadPassword" value="">
											</div>
										</div>
										
										<div class="form-row">
											<div class="form-group col-md-6">
												<div class="badge badge-warning">${passwordError}</div>
											</div>
										</div>
										
										<!-- Form Row        -->
										<div class="form-row">
											<!-- Form Group (organization name)-->
											<div class="form-group col-md-6">
												<label class="small mb-1" for="dni">Tu DNI</label>
												<input class="form-control" id="dni" type="text" name = "readDni" value="<%=userLogin.getDni()%>" readonly>
											</div>
											<!-- Form Group (location)-->
											<div class="form-group col-md-6">
												<label class="small mb-1" for="cuil">Tu CUIL</label>
												<input class="form-control" id="cuil" type="text" name = "readCbu" value="<%=userLogin.getCuil()%>" readonly >
											</div>
										</div>
										<div class="form-row">			
											<div class="form-group col-md-3">
												<label class="small mb-1" for="gender">Género</label>
												<select class="custom-select" id="inputGroupSelect02" name="textGenero" required>
													<option selected><%=userLogin.getGender()%></option>
													<option value="1">Femenino</option>
													<option value="2">Masculino</option>
													<option value="3">Otro</option>
												</select>
												
											</div>											
											
											<%
												List<Nationality> listNat = new ArrayList<Nationality>();
												if(request.getAttribute("listNat") != null)
												{
													listNat = (List<Nationality>) request.getAttribute("listNat");
												}
											%>
											<div class="form-group col-md-3">
			    							<label for="Nationality">Nacionalidad</label>
			    								<select class="form-control" name="textNacionalidad">
			    								<option selected value="<%=userLogin.getNation().getId()%>"><%=userLogin.getNation().getDescription()%></option>
			    								<%
			    									for(Nationality n : listNat)	{
												%>
													
											      	<option value="<%=n.getId()%>"><%=n.getDescription()%></option>
											   	<%
													} 
												%>
											    </select>
											</div>

											<div class="form-group col-md-3">
												<label class="small mb-1" for="nac">Fecha de Nacimiento</label>
												<input class="form-control" id="nac" type="text" name = "readBirthDate" value="<%=userLogin.getBirthDate()%>" >
											</div>
											</div>
											<div class="form-group">
												<label class="small mb-1" for="address">Tu Dirección</label> 
												<input class="form-control" id="address" type="text" name = "readAddress" value="<%=userLogin.getAddress()%>">
											</div>
											<!-- Form Group (email address)-->
											<div class="form-group">
												<label class="small mb-1" for="email">Tu Email</label> 
												<input class="form-control" id="email" type="email" name = "readEmail" value="<%=userLogin.getEmail()%>">
											</div>
											<!-- Form Row-->
											<div class="form-row">
												<!-- Form Group (phone number)-->
												<div class="form-group col-md-3">
													<label class="small mb-1" for="phone">esto hay que hacerlo de cero...</label> 
													<input class="form-control" id="phone" type="text" name = "readphone" readonly>
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-3">
													<input class="btn btn-primary mt-2" type="submit" name="btnModificardatos" value="Modificar datos">
												</div>
											</div>
										</div>
 									</form>
								</div>
							</div>
						</div>
					</div>

					<div class="col-xl-12">
						<!-- Dropdown No Arrow -->
						<div class="card mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary text-center">Soporte
									a cliente</h6>
							</div>
							<div class="card-body">
								<p class="text-center">Llamar a soporte técnico desde 09hs
									a 13hs para cualquier consulta : 11 6849-7568</p>
							</div>
						</div>

					</div>
					<!-- Content Row -->

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->
		</div>	
		<!-- End of Content Wrapper -->
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"
		type="text/javascript"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"
		type="text/javascript"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js" type="text/javascript"></script>

	<!-- Page level plugins -->
	<script src="vendor/chart.js/Chart.min.js" type="text/javascript"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/chart-area-demo.js" type="text/javascript"></script>
	<script src="js/demo/chart-pie-demo.js" type="text/javascript"></script>
</body>
</html>