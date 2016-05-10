package net.ubilife.hellomvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model, @RequestParam(value="n", defaultValue="Anonymous") String name) {
		
		model.addAttribute("name", name);
		
		return "hello";
	}
	
	@RequestMapping(value = "/bye/{name}", method = RequestMethod.GET)
	public ModelAndView bye( @PathVariable String name) {
		
		ModelAndView mv = new ModelAndView("bye");
		mv.addObject("name", name);
		
		return mv;
	}
	
	// This method shows how to map form fields to method params.
	@RequestMapping(value = "/cheers", method = RequestMethod.POST)
	public ModelAndView cheers(String name, int age) {
		
		ModelAndView mv = new ModelAndView("cheers");
		mv.addObject("name", name);
		mv.addObject("age", age);
		return mv;
	}
	
	// This method shows how to use a wrapper class to contain form fields. 	
	@RequestMapping(value = "/cheers2", method = RequestMethod.POST)
	public ModelAndView cheers2(@ModelAttribute Person person) {
		
		ModelAndView mv = new ModelAndView("cheers");
		mv.addObject("name", person.getName());
		mv.addObject("age", person.getAge());
		return mv;
	}
	
	
}
