package toy.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toy.SessionConst;
import toy.domain.ItemDTO;
import toy.domain.ItemPage;
import toy.domain.UserDTO;
import toy.domain.UserRegisterDTO;
import toy.file.FileStore;
import toy.service.ItemService;
import toy.service.UserService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class MainController {
    final ItemService itemService;
    final UserService userService;
    final FileStore fileStore;
    final MessageSource ms;

    @GetMapping
    public String mainRedirect() {
        return "redirect:/shop/1";
    }

    @GetMapping("/{pageNum}")
    public String mainView(@SessionAttribute(name = SessionConst.LOGIN_USER,required = false) UserDTO userDTO,
                           Model model, @PathVariable int pageNum) {
        ItemPage itemPage=new ItemPage(itemService.itemPage(pageNum));
        model.addAttribute("ItemPage",itemPage);
        if(userDTO==null) return "index";
        model.addAttribute("UserDTO",userDTO);
        return "login_index";
    }

    @GetMapping("/register")
    public String register_get(Model model) {
        model.addAttribute("RegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register_post(@Valid @ModelAttribute UserRegisterDTO RegisterDTO, BindingResult bindingResult) {
        String registered = userService.register(RegisterDTO);
        if(registered==null) {
            bindingResult.reject("exist_id",ms.getMessage("error.exist_id",null,null));
        }
        return "redirect:/shop/1";
    }

    @GetMapping("/login")
    public String loginpage(Model model) {
        model.addAttribute("UserDTO",new UserDTO());
        return "login_form";
    }

    @PostMapping("/login")
    public String loginlogic(@Valid @ModelAttribute("UserDTO") UserDTO userDTO, BindingResult bindingResult,
                             HttpServletRequest request) {
        if(bindingResult.hasErrors()) return "login_form";
        UserDTO loginUser = userService.login(userDTO.getUser_id(), userDTO.getPassword());
        if(loginUser==null) {
            bindingResult.reject("loginfail",ms.getMessage("error.loginFail",null,null));
            return "login_form";
        }
        HttpSession session=request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER,loginUser);

        return "redirect:/shop";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null) session.invalidate();
        return "redirect:/shop/1";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam String id) {
        return "mypage";
    }

    @GetMapping("/items/{itemId}")
    public String itemview(@PathVariable Long itemId) {
        return "item";
    }

    @GetMapping("/itemform")
    public String itemForm(Model model) {
        model.addAttribute("ItemDTO",new ItemDTO());
        return "itemform";
    }

    @PostMapping("/itemform")
    public String itemSave(@Valid @ModelAttribute ItemDTO itemDTO, @RequestParam MultipartFile file) throws IOException {
        String uuid=fileStore.fileUpload(file);
        itemService.save(itemDTO,uuid);
        return "redirect:/shop/1";
    }
}
