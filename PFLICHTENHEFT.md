# Zielbestimmung
Ziel des Projektes ist das Erstellen eines CRM-System-Prototypen. Die Lösung wird es Anwendern ermöglichen, Kunden zu verwalten und zu strukturieren. Zusätzlich wird die Lösung Anwendern helfen, Kundenbeziehungen zu pflegen und zu dokumentieren.

### Muss-Kriterien
- Arbeitsbereichmanagement
- Kundenverwaltung
- Ereignisverwaltung
- Standalone Anwendung

### Kann-Kriterien
- Grafische Darstellung der Kundenereignisse
- Export der Kundendaten in CSV - Dateien 
- Notizverwaltung
- Benutzerfreundlichkeit durch Icons

### Abgrenzungskriterien
Folgende Features werden **nicht** implementiert:
- Warenverwaltung
- Verkaufsmanagement
- Mehrbenutzerfähigkeit

# Einsatz
### Anwendungsbereiche
-	Innerhalb des CRM zur internen Organisation
- Innerhalb des CRM zur Verwaltung des Kundenstamms
-	Innerhalb des CRM zur systematischen Gestaltung der Kundenbeziehungsprozesse

### Zielgruppe
- Einzele CRM-Mitarbeiter, welche für zugewiesenen Kunden für das Beziehungsmanagement verantwortlich sind.

# Umgebungsbedingungen
### Systemanforderderungen
* Betriebssytem mit einem Desktop-Environmet
* Java JRE fähiges Gerät
  * Java SE Runtime Environment 8u171 wird vorrausgesetzt
  
### Hardwareanforderrungen
* Bildschirm mit HD-Auflösung >= (1280x720)
* \>= 500 MB freier Arbeitsspeicher
* \>= 1 GB freier Festplattenspeicher

# Funktionalität
Die folgende Funktionalitätsbeschreibung erfolgt aus Nutzersicht.

### Kunden
- Das System muss in der Lage sein neue Kunden anzulegen
- Das System muss in der Lage sein Kunden zu entfernen
- Das System muss in der Lage sein Kundendaten zu bearbeiten
- Das System muss in der Lage sein Datensätze persistent zu speichern
- Das System muss in der Lage sein persistente Datensätze zu laden

### Kommunikation
- Das System muss in der Lage sein Kunden nach Informationen/tags zu filtern
- Das System muss in der Lage sein Kunden direkt aus dem System E-Mails zu schicken
- Das System soll in der Lage sein terminisierte E-Mails automatisch an Kunden zu schicken
- Das System soll in der Lage sein Termine mit einem Kunden zu erstellen
- Das System soll in der Lage sein Erinnerungen zu Terminen einzublenden (z.B. 14:00 Uhr - Max Muster anrufen)
- Das System muss ein Aktivitätenprotokollposten (APP) für Kunden haben
- Das System muss bei Interaktionen mit Kunden registrieren können
- Das System muss die Interaktion in den APP des Kunden eintragen
- Das System muss bei Änderung der Kundendaten die Änderung im APP vermerken

### Aufträge und Anhänge
- Das System muss in der Lage sein Aufträge Kunden zuzuordnen
- Das System soll in der Lage sein Word Dateien an Datensätze zu hängen

## NICHT FUNKTIONAL:

- Fenster soll vergrößerbar bzw. verkleinerbar sein
- Die GUI soll ein Kalender beinhalten
- Wenn man auf einen Datum drückt soll man eine Übersicht aller Termine des Tages erhalten

# Persistente Daten
### Folgende Daten müssen persistent speicherbar/ladbar sein:

- [ ] Arbeitsplatz
- Kunden
  - [ ] Importieren von Kundendateien
  - [ ] Speichern von Kunden
  
*Die Ereignisobjekte gehören entwerder zu Kunden oder zu Arbeitsbereichen und werden deshalb in der Kundendatei oder im Arbeitsbereich gespeichert.*

Wie wir mit den umgegangen,
wie lange
herstellbar
wer ist verantwortlich?
zugang /verschlüsselung?

# Leistungen

# Benutzeroberfläche
* Intuitiv

* Übersichtlich
* Leicht bedienbar

# Qualitätsziele

- Erweiterbarkeit
- Skalierbarkeit
- Intuitivität

* leicht bedienbar
* Maussteuerung

# Qualitätsziele
* **Benutzbarkeit**
  * Iteraktionsfeedback
  
