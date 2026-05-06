
Currently Working on getting into modding minecraft in general.

Progress
- GUI api methods for quick gui devopment complete
    Base screen for renderning created
    base menu for logical server side connection created
    Conditional Gated Widget 
    Dynamic Label Widget
    Clickable Texture Widget (highlight- and selectable)
- Packet structure for entity events established
- Functional and interactable core GUIs complete
    DeckSceen
    PatternScreen
- Chegg Deck custom item created
    Interface to main deck building system
- Base item and block registering methods created
- Dynamic deck rule manager created, accessible through deck menu
    (might need general access)
- Deck memory manager created
    importand for screen deck connection
-JSON loader added to core
-Defined editable pattern structures and special ability params, editable via json for admins to set default values for the server



To-do
- Screen to menu connection API
- Payload registering
- cleanup of main mod dir
- Arena multiblock
- Eggter (Chegg scepter item to control the game with)
      needs to show available Cheggs
      needs to be able to select available Cheggs
      needs to be able to perform operations on entities on the board
      needs to be able to pass turns
- Chegg Rulebook (still considering)
      Tweak rules like mana costs, attack/movement/ability patterns and maybe some special ability features and tie these rules to a book
      on the arena multiblock, you can insert it to set your own rules, if you dislike the default rules
- Chegg Entity logic needs to be implemented
      needs to be visibile on the chegg deck gui pattern screen
          make entities draggable to and set buttons to pattern screen for movement/attack/special
          set dummy zombie entities and a chegg king so people can test entities on the board freely
