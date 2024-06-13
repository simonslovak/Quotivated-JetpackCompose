---
subtitle: "Úvod:"
title: Bomber
---

Bomber je klasická arkádová hra, ve které hráč ovládá postavičku, která
musí shodit bomby, aby zničila překážky a soupeře. Cílem je zničit
nepřátele.

Kickoff:

**Cíl hry:** Zůstat poslední naživu. Zničit protihráče.

**Hratelnost:** Hra se bude ovládat pomocí šipek (tedy klávesnice). Hru
je možné hrát jako singl player proti počítači, tak jak multi player
proti až 4 hráčům.

**Cílová platforma:** Hra je určena pro PC platformu a bude vyvíjena
v Godot 4.2

Hratelnost:

Hra bude kombinací akčního a strategického stylu, kde hráč bude muset
rychle reagovat na situace a zároveň používat strategické přístupy k
dosažení cíle.

-   **Akční prvky:** Hráč bude muset rychle pohybovat postavou a položit
    bomby na strategických místech, aby zničil nepřátel.

-   **Strategické prvky:** Kromě pouhého bombardování bude hráč muset
    strategicky plánovat své akce, vyhýbat se nepřátelům a přemýšlet o
    tom, jak využít terén a power-upy k dosažení vítězství.

**Ovládání:**

Hráč bude ovládat postavu pomocí šipek na klávesnici:

-   **Hráč 1 (Šipky):**

    -   **Šipka nahoru:** Pohyb nahoru

    -   **Šipka dolů:** Pohyb dolů

    -   **Šipka doleva:** Pohyb doleva

    -   **Šipka doprava:** Pohyb doprava

    -   **Klávesa pro položení bomby:** Mezerník

-   **Hráč 2 (WASD):**

    -   **Klávesa W:** Pohyb nahoru

    -   **Klávesa S:** Pohyb dolů

    -   **Klávesa A:** Pohyb doleva

    -   **Klávesa D:** Pohyb doprava

    -   **Klávesa pro položení bomby:** Klávesa E

```{=html}
<!-- -->
```
-   **Hráč 3 (JIKL):**

    -   **Klávesa J:** Pohyb nahoru

    -   **Klávesa K:** Pohyb dolů

    -   **Klávesa I:** Pohyb doleva

    -   **Klávesa L:** Pohyb doprava

    -   **Klávesa pro položení bomby:** Klávesa O

-   **Hráč 4 (NumPad):**

    -   **Klávesa 8:** Pohyb nahoru

    -   **Klávesa 2:** Pohyb dolů

    -   **Klávesa 4:** Pohyb doleva

    -   **Klávesa 6:** Pohyb doprava

    -   **Klávesa pro položení bomby:** Klávesa 0

Primárně bude hra vyvíjena jen pro dva hráče.

Ukázka, jak by mohly vypadat postavičky. Postavičky se budou lišit
barvou.

> ![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image1.png){width="2.951388888888889in"
> height="2.951388888888889in"}![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image2.png){width="2.8in"
> height="2.8in"}

**Singleplayer:**

V jednohráčovém režimu hráč bude bojovat proti umělé inteligenci, která
bude kontrolovat nepřátele. Hra bude nabízet různé úrovně obtížnosti a
postupně se zvyšující výzvy. Například počet nepřátel apd. (Primárně
bude hra pouze jako multiplayer)

**Multiplayer:**

V multiplayer režimu hráči budou moci soutěžit proti sobě. Možnosti
multiplayeru zahrnuje lokální multiplayer na jednom zařízení

**Další prvky:**

-   **Power-upy:**

    -   V herním světě se budou nacházet různé power-upy, které
        poskytnou hráči výhodu, jako je zvýšení rychlosti, silnější
        bomby nebo větší počet bomb.

    -   Power-upy by mohly vypadat nějak takto:

![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image3.png){width="1.6236111111111111in"
height="1.6236111111111111in"}![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image4.png){width="1.6069444444444445in"
height="1.6069444444444445in"}![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image5.png){width="1.6236111111111111in"
height="1.6236111111111111in"}

-   **Různorodé bloky:**

    -   Hra bude obsahovat rozdílné bloky, které budou tvořit herní
        svět.

    -   Překážky, které se dají/nedají zničit (vnější a vnitřní)

> ![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image6.png){width="1.7756944444444445in"
> height="1.7756944444444445in"}![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image7.png){width="1.7756944444444445in"
> height="1.7756944444444445in"}![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image8.png){width="1.7438888888888888in"
> height="1.7438888888888888in"}

Mechanismy hry:

**Pohyb postav:**

Hráči budou moci pohybovat svou postavou po herní mapě pomocí šipek nebo
kláves na klávesnici.

Pohyb bude omezen na čtvercovou mřížku.

**Položení bomb:**

Hráči budou moci položit bomby na volné pozice na herní mapě.

Omezený počet bomb, které hráč může položit současně.

**Exploze bomb:**

Bomba vytvoří explozi v horizontálním a vertikálním směru od svého
umístění.

Exploze může poškodit hráče, nepřátele a prostředí v jejím dosahu.

Princip mapy:

**2D mapa v čtvercové mřížce:**

Mapa bude mít formát čtvercové mřížky, kde každý čtvereček představuje
jednotlivý herní políčko.

Velikost mapy bude předem definována a může se lišit v závislosti na
konkrétní úrovni nebo nastavení hry.

Hráči budou moci pohybovat po této mřížce pouze horizontálně a
vertikálně.

**Překážky a volná místa:**

Na mapě budou umístěny různé překážky, jako jsou zdi, bloky nebo
propasti, které ovlivní pohyb hráčů a exploze bomb.

Mezi překážkami budou také volná místa, kde hráči mohou pohodlně
pohybovat a bojovat.

**Generování mapy:**

Mapy mohou být buď předdefinované pro každou úroveň, nebo mohou být
generovány náhodně, což dodává hře variabilitu a nové výzvy.

![Obsah obrázku snímek obrazovky, čtverec Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image9.png){width="6.3in"
height="4.434027777777778in"}

Design výbuchu bomby

![Obsah obrázku kruh, Grafika, snímek obrazovky, design Popis byl
vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image10.png){width="1.2879997812773403in"
height="1.3109448818897638in"}
![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image11.png){width="1.3034995625546806in"
height="1.3034995625546806in"}![Obsah obrázku snímek obrazovky, symbol,
kruh, Grafika Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image12.png){width="1.3120002187226596in"
height="1.3120002187226596in"}![Obsah obrázku snímek obrazovky, kruh,
Grafika, symbol Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image13.png){width="1.3040004374453193in"
height="1.3040004374453193in"}![Obsah obrázku kruh, Grafika, Barevnost,
snímek obrazovky Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image14.png){width="1.2552777777777777in"
height="1.2552777777777777in"} ![Obsah obrázku kruh, Grafika, Barevnost,
symbol Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image15.png){width="1.215777559055118in"
height="1.215777559055118in"}

![Obsah obrázku umění, Grafika, pixel, hvězda Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image16.png){width="1.039527559055118in"
height="1.039527559055118in"}
![](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image17.png){width="1.031388888888889in"
height="1.031388888888889in"} ![Obsah obrázku žlutá, oranžová, Jantar
Popis byl vytvořen
automaticky](vertopal_90b7032fc4564c6bb77892ac03bf9d40/media/image18.png){width="1.0398326771653543in"
height="1.0398326771653543in"}

Struktura hry:

1.  **Úvodní obrazovka:**

-   Po spuštění hry se objeví úvodní obrazovka s názvem hry a možnostmi,
    jako je \"Hrát\", \"Nastavení\", \"Nápověda\" a \"Opustit\".

-   Hráč může vybrat možnost \"Hrát\", aby začal novou hru Bomber.

2.  **Volba počtu hráčů:**

-   Po zvolení možnosti \"Hrát\" se hráči budou moci rozhodnout, kolik
    hráčů se bude účastnit hry. Možnosti mohou být 1 až 4 hráči.

-   Pokud hraje více hráčů, budou moci hrát na jednom počítači

3.  **Výběr postav:**

-   Každý hráč si vybere svou postavu, která může mít různé vlastnosti
    nebo vzhled.

-   Po výběru postav se hráči přesunou na obrazovku s výběrem úrovně.

4.  **Výběr úrovně:**

-   Hráči vyberou úroveň, na které chtějí hrát. Může jít o předem
    vytvořené úrovně nebo o náhodně generované úrovně.

-   Po výběru úrovně se hráči přesunou na herní obrazovku.

5.  **Herní obrazovka:**

-   Hra začíná a hráči se objeví na herní mapě, která je čtvercové
    mřížce.

-   Hráči mohou začít pohybem po mapě a položením bomb.

-   Cílem je přežít co nejdéle a zničit ostatní hráče pomocí bomb.

-   Power-upy se mohou objevit na mapě a hráči se mohou snažit je
    získat.

6.  **Konec hry:**

-   Hra končí, když zbývá pouze jeden hráč naživu.

-   Po ukončení hry se zobrazí výsledková obrazovka s informacemi o
    vítězi a skóre každého hráče.

-   Hráči se mohou vrátit na úvodní obrazovku a zvolit novou hru nebo
    ukončit hru.
