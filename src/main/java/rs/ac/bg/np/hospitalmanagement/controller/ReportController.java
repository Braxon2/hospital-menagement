package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.domain.Report;
import rs.ac.bg.np.hospitalmanagement.service.ReportService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAll(){
        return reportService.getAll();
    }

    @GetMapping("/{repid}")
    public Report getReport(@PathVariable long repid) throws Exception {
        return reportService.getReport(repid);
    }

    @PostMapping("/{docid}/for/{pid}/with/{did}")
    public Report doctorNewReport(@PathVariable long docid, @RequestBody Report report,
                                  @PathVariable long pid,@PathVariable long did) throws Exception {
        return reportService.doctorNewReport(docid,report, pid,did);
    }

    @GetMapping("/doctor/{docid}")
    public Set<Report> getAllReportsFromDoctor(@PathVariable long docid) throws Exception {
        return reportService.getAllReportsFromDoctor(docid);
    }

    @GetMapping("/patient/{pid}")
    public Set<Report> getAllReportsFromPatient(@PathVariable long pid) throws Exception {
        return reportService.getAllReportsFromPatient(pid);
    }


}
