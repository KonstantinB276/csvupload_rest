package de.db.csvupload.springboot.csvupload_rest.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.db.csvupload.springboot.csvupload_rest.entity.Betriebsstelle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UploadController {

    private List<Betriebsstelle> betriebsstelleList = new ArrayList<>();

    @GetMapping("/")
    public String chooseCSV() {
        return "chooseCSV";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Betriebsstelle> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Betriebsstelle.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .withIgnoreQuotations(true)
                        .withThrowExceptions(false)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build();
                betriebsstelleList = csvToBean.parse();
//                model.addAttribute("betriebsstelleList", betriebsstelleList);
//                model.addAttribute("status", true);
                model.addAttribute("message", "Success.");
                model.addAttribute("status", false);
            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    @RequestMapping(value ="/betriebsstellen", method=RequestMethod.GET)
    @ResponseBody
    public List<Betriebsstelle> getJSON(){
        return betriebsstelleList;
    }

    @RequestMapping(value ="/betriebsstelle/{code}", method=RequestMethod.GET)
    @ResponseBody
    public List<Betriebsstelle> getBsJSON(@PathVariable("code") String code){

        List<Betriebsstelle> filteredBsList = betriebsstelleList.stream().filter(bs
                -> bs.getCode().equalsIgnoreCase(code)).collect(Collectors.toList());

        return filteredBsList;
    }

}
