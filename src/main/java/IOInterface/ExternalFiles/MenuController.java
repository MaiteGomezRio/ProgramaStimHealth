package IOInterface.ExternalFiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/menu")
	
	public String mostrarMenu(Model model) {
		return "menu";
	}
}
