(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Initiate the wowjs
    new WOW().init();


    // Sticky Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.sticky-top').css('top', '0px');
        } else {
            $('.sticky-top').css('top', '-100px');
        }
    });
    
    
    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";
    
    $(window).on("load resize", function() {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
            function() {
                const $this = $(this);
                $this.addClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "true");
                $this.find($dropdownMenu).addClass(showClass);
            },
            function() {
                const $this = $(this);
                $this.removeClass(showClass);
                $this.find($dropdownToggle).attr("aria-expanded", "false");
                $this.find($dropdownMenu).removeClass(showClass);
            }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Header carousel
    $(".header-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        items: 1,
        dots: false,
        loop: true,
        nav : true,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ]
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        center: true,
        margin: 24,
        dots: true,
        loop: true,
        nav : false,
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });

    
})(jQuery);
    axios.get('/learning_edu/menus')
    .then(function(response) {
        const menus = response.data; // Lấy dữ liệu JSON từ API
        const menuContainer = document.getElementById('menu-container');
        let menuHtml = ''; // Chuỗi HTML để chứa các menu

        menus.forEach(menu => {
            if (menu.subMenus.length > 0) {
                // Nếu có submenu
                let subMenuHtml = '';
                menu.subMenus.forEach(subMenu => {
                    subMenuHtml += `<a href="${subMenu.link}" class="dropdown-item">${subMenu.title}</a>`;
                });

                // Thêm dropdown menu
                menuHtml += `
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${menu.title}</a>
                        <div class="dropdown-menu fade-down m-0">
                            ${subMenuHtml}
                        </div>
                    </div>`;
            } else {
                // Nếu không có submenu, chỉ là link đơn giản
                menuHtml += `<a href="${menu.link}" class="nav-item nav-link">${menu.title}</a>`;
            }
        });

        // Chèn HTML vào container
        menuContainer.innerHTML = menuHtml;
    })
    .catch(function(error) {
        console.log('Error fetching menu:', error);
    });

axios.get("/learning_edu/Blogs/show_BlogUser").then((response)=>{
    const listBlogs = response.data;
    const showBlog = document.getElementById("blogShow");
    let innerHtml = '';

    listBlogs.forEach(listBlog =>{
        innerHtml += `
        <div class="col-lg-4 wow fadeInUp" data-wow-delay="0.5s">
        <div class="blog-item bg-light rounded overflow-hidden">
                                              <div class="blog-img position-relative overflow-hidden">
                                                  <img class="img-fluid" src="${listBlog.image}" alt="">
                                                  <a class="position-absolute top-0 start-0 bg-primary text-white rounded-end mt-3 py-2 px-4" href="">${listBlog.category.title}</a>
                                              </div>
                                              <div class="p-4">
                                                  <div class="d-flex mb-3">
                                                      <small class="me-3"><i class="fa fa-user text-primary me-2"></i>${listBlog.user.lastname}</small>
                                                      <small><i class="fa fa-calendar text-primary me-2"></i>${listBlog.datecreate}</small>
                                                  </div>
                                                  <h4 class="mb-3">${listBlog.title}</h4>
                                                  <p>${listBlog.content}</p>
                                                  <a class="text-uppercase" href="/learning_edu/get_blogs?id=${listBlog.blogID}">Xem thêm <i class="bi bi-arrow-right"></i></a>
                                              </div>
                                          </div>
                                          </div>`;
    });
    showBlog.innerHTML = innerHtml;
});

