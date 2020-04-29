% VSIS-Aufgabenblatt
%
% LaTeX-Klasse zur Erstellung von Aufgabenblaettern inkl. optionaler Musterloesung im VSIS-Stil.
%
% Entworfen von Wolfram Wingerath auf Grundlage eines existierenden Designs von Kristof Hamann
% Modifiziert von Felix Kiehn
%
% Changelog:
%
% v1.1:
% 2018-11-22 FK: Default-Logo in Kopfzeile (VSIS-Logo) durch DBIS-Logo ersetzt
%
% v1.0:
% 20??-??-?? WW: Erste Version der GDB-Aufgabenblattvorlage

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{gdb-aufgabenblatt}[2018/11/22 v1.1 GDB-Aufgabenblaetter]

\LoadClass{vsis-aufgabenblatt}


\newcommand{\Gruppe}{*Nachnamen der Teilnehmer*}
\newcommand{\STiNEGruppeTitel}{STiNE-Gruppe}
\newcommand{\STiNEGruppe}{14}


\renewcommand{\Vorlesung}{Databases and Information Systems 2020}
\renewcommand{\Logo}{dbis.pdf}
%\renewcommand{\Semester}{WS 2014/15}
\renewcommand{\STiNEGruppe}{14}
\renewcommand{\PunktezahlTitel}{\STiNEGruppeTitel~\STiNEGruppe}
\renewcommand{\Punktezahl}{\Gruppe}