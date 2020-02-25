package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.or.connect.reservation.interceptor.LogInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.connect.reservation.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
 
    // default servlet handler를 사용하게 한다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    // 해당 컨텍스트의 uri와 매칭되는 view 설정
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("mainpage");
        registry.addViewController("/detail").setViewName("detail");
        registry.addViewController("/review").setViewName("review");
        registry.addViewController("/reserve").setViewName("reserve");
        registry.addViewController("/bookinglogin").setViewName("bookinglogin");
        registry.addViewController("/myreservation").setViewName("myreservation");
        registry.addViewController("/reviewWrite").setViewName("reviewWrite");
    }
    
    // 사용하고자 하는 인터셉터 객체 등록
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new LogInterceptor());
	}
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    /*
     * 	DispathcerServlet에게 멀티파트 요청이 올경우 파일 업로드 처리가 될 수 있도록 MultipartResolver객체를 등록한다.
	 *	최대 10메가 크기의 파일이 저장되도록 설정하였다.
     */
    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 ( 1MB = 10^6바이트 = 2^20비트)
        return multipartResolver;
    }
}