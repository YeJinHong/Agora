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
                                <a href="javascript:void(0);">Back to Home</a>
                            </div>
                        </div>
                        <h1>Enter Varification Code</h1>
                        <div class="reset-password">
                            <p>We have just sent a verification code to your registed email id</p>
                        </div>
                        <form action="login" class="verification-code">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="d-flex digit-group">
                                        <input type="text" class="form-control" id="digit-1" name="digit-1" data-next="digit-2" value="8" />
                                        <input type="text" class="form-control" id="digit-2" name="digit-2" data-next="digit-3" data-previous="digit-1" />
                                        <input type="text" class="form-control" id="digit-3" name="digit-3" data-next="digit-4" data-previous="digit-2" />
                                        <input type="text"  class="form-control" id="digit-4" name="digit-4" data-next="digit-5" data-previous="digit-3" />
                                    </div>   
                                </div>
                                <div class="send-code">
                                    <router-link to="verification-code">Send the code again</router-link>
                                </div>
                                <div class="d-grid">
                                    <button class="btn btn-primary btn-start" type="submit">Submit</button>
                                </div>
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
<script>
    import Vue from 'vue'
    export default {
      components: {
        
      },
      data() {
            return {
               
               
            }
        },
        mounted() {

            $('.digit-group').find('input').each(function() {
		$(this).attr('maxlength', 1);
			$(this).on('keyup', function(e) {
				var parent = $($(this).parent());
	
				if(e.keyCode === 8 || e.keyCode === 37) {
					var prev = parent.find('input#' + $(this).data('previous'));
					
					if(prev.length) {
						$(prev).select();
					}
				} else if((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 65 && e.keyCode <= 90) || (e.keyCode >= 96 && e.keyCode <= 105) || e.keyCode === 39) {
					var next = parent.find('input#' + $(this).data('next'));
					
					if(next.length) {
						$(next).select();
					} else {
						if(parent.data('autosubmit')) {
							parent.submit();
						}
					}
				}
			});
		});
	
		$('.digit-group input').on('keyup', function(){
			var self = $(this);
			if ( self.val() != '' ) {
				self.addClass('active');
			} else {
				self.removeClass('active');
			}
		});
        }
    }
</script>