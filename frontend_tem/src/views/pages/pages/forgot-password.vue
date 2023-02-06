<template>
    <!-- Main Wrapper -->
    <div class="main-wrapper">
        
        <div class="row">
			
            <loginbanner></loginbanner>
            
            <div class="col-md-6 login-wrap-bg">		
            
                <!-- Login -->
                <div class="login-wrapper">
                    <div class="loginbox">
                        <div class="img-logo">
                            <img src="../../../assets/img/logo.svg" class="img-fluid" alt="Logo">
                            <div class="back-home">
                                <router-link to="/">Back to Home</router-link>
                            </div>
                        </div>
                        <h1>Forgot Password ?</h1>
                        <div class="reset-password">
                            <p>Enter your email to reset your password.</p>
                        </div>
                        <form action="login">
                            <div class="form-group">
                                <label class="form-control-label">Email</label>
                                <input type="email" class="form-control" placeholder="Enter your email address">
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-start" type="submit">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /Login -->
                
            </div>
            
        </div>
       
    </div>
    <!-- /Main Wrapper -->
</template>
