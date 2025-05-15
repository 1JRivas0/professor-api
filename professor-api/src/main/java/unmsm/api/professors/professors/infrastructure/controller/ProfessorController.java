package unmsm.api.professors.professors.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unmsm.api.professors.professors.application.dto.ProfessorResponse;
import unmsm.api.professors.professors.domain.ports.input.GetProfessorsUseCase;

import java.util.List;

// Controlador REST para manejar todas las operaciones relacionadas con profesores
@RestController
@RequestMapping("/api/professors")  // Ruta base para todos los endpoints
public class ProfessorController {

    // Inyección de dependencia del caso de uso principal
    private final GetProfessorsUseCase getProfessorsUseCase;
    
    // Logger para seguimiento de actividades
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    // Constructor con inyección de dependencia automática
    @Autowired
    public ProfessorController(GetProfessorsUseCase getProfessorsUseCase) {
        this.getProfessorsUseCase = getProfessorsUseCase;
    }

// @PostMapping para crear recursos
// @PutMapping para actualizaciones
// @DeleteMapping para eliminar recursos

    // Endpoint 1: Obtener todos los profesores
    @GetMapping
    public List<ProfessorResponse> getAllProfessors() {
        logger.info("Getting all professors");  // Log nivel info
        logger.debug("Número de profesores: {}", getProfessorsUseCase.getAllProfessors().size());  // Log nivel debug
        return getProfessorsUseCase.getAllProfessors();
    }

    // Endpoint 2: Buscar profesor por ID
    @GetMapping("/{id}")
    public ProfessorResponse getProfessorById(@PathVariable String id) {
        logger.info("Buscando profesor con ID: {}", id);
        return getProfessorsUseCase.getProfessorById(id);
    }

    // Endpoint 3: Filtrar por facultad
    @GetMapping("/faculty/{faculty}")
    public List<ProfessorResponse> getProfessorsByFaculty(@PathVariable String faculty) {
        logger.info("Filtrando por facultad: {}", faculty);
        return getProfessorsUseCase.getProfessorsByFaculty(faculty);
    }

    // Endpoint 4: Buscar por nombre (búsqueda parcial insensible a mayúsculas)
    @GetMapping("/name/{name}")
    public List<ProfessorResponse> getProfessorsByName(@PathVariable String name) {
        logger.info("Buscando por nombre: {}", name);
        return getProfessorsUseCase.getProfessorsByName(name);
    }

    // Endpoint 5: Filtrar por curso dictado
    @GetMapping("/course/{course}")
    public List<ProfessorResponse> getProfessorsByCourse(@PathVariable String course) {
        logger.info("Filtrando por curso: {}", course);
        return getProfessorsUseCase.getProfessorsByCourse(course);
    }
}
