package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import net.skhu.dto.Bus;
import net.skhu.mapper.BusMapper;
import net.skhu.mapper.CategoryMapper;
import net.skhu.model.Pagination;

@Controller
@RequestMapping("bus")
public class BusController {

    @Autowired CategoryMapper categoryMapper;
    @Autowired BusMapper busMapper;

    @RequestMapping("list")
    public String list(Model model, Pagination pagination) {
        model.addAttribute("buses", busMapper.findAll(pagination));
        pagination.setRecordCount(busMapper.getCount());
        return "bus/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("categories", categoryMapper.findAll());
        return "bus/edit";
    }

    @PostMapping("create")
    public String create(Model model, Pagination pagination,
            @Valid Bus bus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryMapper.findAll());
            return "bus/edit";
        }
        busMapper.insert(bus);
        int lastPage = (int)Math.ceil((double)busMapper.getCount() / pagination.getSz());
        pagination.setPg(lastPage);
        return "redirect:list?" + pagination.getQueryString();
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id, Pagination pagination) {
        model.addAttribute("bus", busMapper.findOne(id));
        model.addAttribute("categories", categoryMapper.findAll());
        return "bus/edit";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, Pagination pagination,
            @Valid Bus bus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryMapper.findAll());
            return "bus/edit";
        }
        busMapper.update(bus);
        return "redirect:list?" + pagination.getQueryString();
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, @RequestParam("id") int id, Pagination pagination) {
        busMapper.deleteById(id);
        return "redirect:list?" + pagination.getQueryString();
    }
}
