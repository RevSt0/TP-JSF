/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaines;

import entities.Employe;
import entities.Service;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.RowEditEvent;
import service.EmployeService;
import service.ServiceService;

/**
 *
 * @author RevSt
 */
@ManagedBean
public class EmployeBean {

    private Employe employe;
    private Service service;
    private List<Employe> employes;
    private EmployeService employeService;
    private ServiceService serviceService;
    private Employe chef;

    public EmployeBean() {

        employe = new Employe();
        chef = new Employe();
        employeService = new EmployeService();
        serviceService = new ServiceService();

    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public void onCreateAction() {
        if (chef != null) {
            chef = employeService.getById(chef.getId());
            employe.setEmploye(chef);
        }
        employeService.create(employe);
        employe = new Employe();
        chef = new Employe();
    }

    public void onEditAction(RowEditEvent event) {
        employe = (Employe) event.getObject();
        if (chef != null) {
            chef = employeService.getById(chef.getId());
            employe.setEmploye(chef);
        }
        service = serviceService.getById(employe.getService().getId());
        employe.setService(service);
        employeService.update(employe);
        chef = new Employe();
    }

    public void onCancelEdit() {
        //nothing
    }

    public void onDeleteAction() {
        List<Employe> employeesToUpdate = employeService.getByChef(employe);
        for (Employe employee : employeesToUpdate) {
                employee.setEmploye(null);
                employeService.update(employee);
            }
        employe.setEmploye(null);
        employe.setService(null);
        employeService.delete(employeService.getById(employe.getId()));
    }

}
