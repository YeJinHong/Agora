<template>
    <div class="error-page">
        <!-- Main Wrapper -->
        <div class="main-wrapper">
        
            <div class="error-box">
				<div class="error-logo">
					<router-link to="/">
						<img src="../../../../assets/img/logo.svg" class="img-fluid" alt="Logo">
                    </router-link>
				</div>
				<h4>WE ARE COMING SOON!!</h4>
				<h6 class="font-weight-normal">Stay tuned for something amazing</h6>
				<div class="countdown-container">
					<div class="countdown-el days-c">
						<p class="big-text" id="days">0</p>
						<span>Days</span>
					</div>
					<div class="countdown-el hours-c">
						<p class="big-text" id="hours">0</p>
						<span>hrs</span>
					</div>
					<div class="countdown-el mins-c">
						<p class="big-text" id="mins">0</p>
						<span>mins</span>
					</div>
				</div>
				<div class="error-box-img">
					<img src="../../../../assets/img/come-soon.png" alt="" class="img-fluid" >
				</div>
				<div class="come-soon-box">
					<h5 class="h4 font-weight-normal">Subscribe to our mailing list to get latest updates</h5>
					<div class="subscribe-soon">
						<form>
							<div class="input-group mb-3">
								<input type="text" class="form-control" placeholder="Email">
								<button class="btn btn-danger" type="button">Subscribe</button>
							</div>
						</form>
					</div>
					<div class="social-icon-soon">
						<ul>
							<li><a href="javascript:;"><i class="fa-brands fa-facebook face-book"></i></a></li>
							<li><a href="javascript:;"><i class="fa-brands fa-twitter twit-ter"></i></a></li>
							<li><a href="javascript:;"><i class="fa-brands fa-instagram insta-gram"></i></a></li>
							<li><a href="javascript:;"><i class="fa-brands fa-linkedin linked-in"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
       
        </div>
        <!-- /Main Wrapper -->
    </div>
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
        if($('.countdown-container').length > 0 ){
		const daysEl = document.getElementById("days");
		const hoursEl = document.getElementById("hours");
		const minsEl = document.getElementById("mins");

		const newYears = "1 Jan 2023";

		function countdown() {
			const newYearsDate = new Date(newYears);
			const currentDate = new Date();

			const totalSeconds = (newYearsDate - currentDate) / 1000;

			const days = Math.floor(totalSeconds / 3600 / 24);
			const hours = Math.floor(totalSeconds / 3600) % 24;
			const mins = Math.floor(totalSeconds / 60) % 60;

			daysEl.innerHTML = days;
			hoursEl.innerHTML = formatTime(hours);
			minsEl.innerHTML = formatTime(mins);
		}

		function formatTime(time) {
			return time < 10 ? `0${time}` : time;
		}

		// initial call
		countdown();

		setInterval(countdown, 1000);
	}
      },
      methods: {
      
    },
    }
  </Script>