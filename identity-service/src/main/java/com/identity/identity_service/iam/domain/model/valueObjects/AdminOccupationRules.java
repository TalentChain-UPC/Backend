package com.identity.identity_service.iam.domain.model.valueObjects;

import java.util.Map;
import java.util.Set;

public class AdminOccupationRules {
    public static final Map<String, Set<String>> ADMIN_OCCUPATIONS_BY_AREA = Map.of(
            "INFORMATION_TECHNOLOGY", Set.of(
                    "soporte tecnico", "soporte técnico", "soporte tecino", "soporte tec", "soprote tecnico", "soport tecnico",
                    "soporte", "soprote", "sopote", "soportr", "sopore",
                    "mesa de ayuda", "mesade ayuda", "mesa ayuda", "mesa d ayuda", "mes ade ayuda",
                    "help desk", "helpdesk", "help-desk", "held desk", "hel desk", "help dek",
                    "administrador de sistemas", "admin de sistemas", "administrador sistemas", "admnistrador de sistemas",
                    "administrador de red", "admin de red", "administrador red", "administrador d red", "administador red",
                    "administrador de servidores", 	"admin de servidores", "administrador servidores", "admin servidores",
                    "responsable de soporte", "responsable soporte", "responsable d soporte", "resposable de soporte",
                    "especialista en infraestructura", "especialista infraestructura", "esp en infraestructura", "especialsta en infra",
                    "especialista en ti", "especialista en t.i.", "especialista ti", "especialiasta en ti",
                    "coordinador de ti", "coordinador ti", "coordinador d ti", "cordinador de ti", "coordinador de t.i.",
                    "analista de soporte", "analista soporte", "analsita de soporte", "analist de soporte",
                    "técnico informático", "tecnico informatico", "tecnico infromatico", "tecnico infor", "tec informatico",
                    "ingeniero de soporte", "ing de soporte", "ingeniero soporte", "ing soporte", "inge de soporte",
                    "ingeniero de sistemas", "ing de sistemas", "ingeniero sistemas", "ingeniro de sistemas",
                    "responsable de seguridad informática", "responsable seguridad", "resp de seguridad info", "responsable seg informatica",
                    "analista de seguridad", "analista seguridad", "analsita de seguridad", "analista de segurida"
            ),
            "ADMINISTRATION", Set.of(
                    "asistente administrativo", "asistente adm", "asistente admin", "asist administrativo", "asis adm",
                    "coordinador administrativo", "coordinador adm", "coord admin", "cordinador administrativo",
                    "administrador de plataforma", "admin de plataforma", "administrador plataforma", "administrador plat",
                    "gestor de cuentas", "gestor cuentas", "gestor d cuentas", "gest de cuentas", "gestorc uentas",
                    "administrador funcional", 	"admin funcional", "administrador func", "administrador funcinal",
                    "analista administrativo", "analista adm", "analista admin", "analist administrativo"
            ),
            "HUMAN_RESOURCES", Set.of(
                    "analista de recursos humanos", "analista rrhh", "analista rec humanos", "analista recursos", "analsita rrhh",
                    "responsable de recursos humanos", "responsable rrhh", "responsable recursos", "resp recursos humanos",
                    "coordinador de recursos humanos", "coordinador rrhh", "coord recursos", "coord de rrhh",
                    "jefe de personal", "jefe personal", "jfe de personal", "jefe d personal",
                    "encargado de nómina", "encargado nomina", "encargado de nominas", "encargad de nómina",
                    "gestor de talento humano", "gestor talento", "gestor th", "gestor de talento",
                    "administrador de personal", "admin personal", "administrador personal", "administrador d personal"
            ),
            "GENERAL_MANAGEMENT", Set.of(
                    "gerente general", "gerent general", "gerente gral", "gerente gnral",
                    "director de operaciones", "director operaciones", "dir de operaciones", "direcor de operaciones",
                    "coordinador general", 	"coord general", "coordinador gral", "cordinador general",
                    "jefe de unidad", "jefe unidad", "jfe de unidad", "jefe d unidad",
                    "responsable de área", "responsable area", "responsable d área", "resp de area"
            )
    );
}
