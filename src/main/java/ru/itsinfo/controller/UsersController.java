package ru.itsinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itsinfo.model.User;
import ru.itsinfo.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"", "/", "list"})
	public String showAllUsers(Model model, @ModelAttribute("flashMessage") String flashAttribute) {
		model.addAttribute("users", userService.getAllUsers());
		return "list";
	}

	@GetMapping(value = "/new")
	public String addUserForm(@ModelAttribute("user") User user) {
		return "form";
	}

	@GetMapping("/{id}/edit")
	public String edidtUserForm(@PathVariable(value = "id", required = true) long id, Model model, RedirectAttributes attributes) {
		User user = userService.readUser(id);
		if (null == user) {
			attributes.addFlashAttribute("flashMessage", "User are not exists!");
			return "redirect:/users";
		}
		model.addAttribute("user", userService.readUser(id));
		return "form";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, RedirectAttributes attributes) {
		userService.deleteUser(id);
		attributes.addFlashAttribute("flashMessage","User " + userService.readUser(id) + " successfully created!");
		return "redirect:/users";
	}
	@PostMapping()
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		userService.createOrUpdateUser(user);
		attributes.addFlashAttribute("flashMessage",
				"User " + user.getFirstName() + " successfully created!");
		return "redirect:/users";
	}
}