# Cornbread Densened
Cornbread Densened is a spigot plugin designed to make Minecraft more challenging. It is not made to be fun, and you will probably rage-quit relatively quickly upon playing. It is the second version of the original Cornbread plugin, which was a mess of code that I made six months ago and never released.

It is called Cornbread because I wanted it to be called Cornbread.

If you're stuck and need a hint head over to the [tips](https://github.com/Pm7-dev/Cornbread-Densened?tab=readme-ov-file#tips) section\
For a full list of features head over to the [features](https://github.com/Pm7-dev/Cornbread-Densened?tab=readme-ov-file#feature-list) section
# How to Set Up
This tutorial assumes you already have a Spigot server set up. If you do not already know how to set up a Spigot server, many tutorials can be found on YouTube. This plugin will not work on vanilla.
1. Head over to the [releases](https://github.com/Pm7-dev/Cornbread-Densened/releases/) page or the [spigot releases]() and download the latest (probably only) version of the plugin. 
2. Shut down your server if it is running
3. Add the plugin to your plugins folder
4. Edit the `server.properties` file and ensure that the following values are set:
```
level-name=world
require-resource-pack=true
resource-pack=https\://github.com/Pm7-dev/Cornbread-Densened/raw/0d263b82cc46898b3f80c007adbd845a5285061b/cornbread_pack.zip
resource-pack-id=
resource-pack-prompt=
resource-pack-sha1=20b79f3c04f008315e27effc2eb8e7e447855dab
```
5. Either delete the current world folder, along with the nether and end world folders, or move them to a different location if you want to preserve them
6. Add the following text to the end of your `bukkit.yml` file
```
worlds:
  world:
    generator: CornbreadDensened
  world_the_end:
    generator: CornbreadDensened
  ```
7. Start up the server
8. Have a lack of fun!
9. If the server crashes, relaunch it. I think it has to do with the loading of structures but I'm so tired of fixing this
# Feature List
For maximum annoyance value, I suggest playing through the game completely blind. However, if you want to know exactly what you're getting yourself into, click the "Show Feature List" button to see what exactly the plugin does
<details>
  <summary>Show Feature List</summary>

- There is a custom world generator built specifically for this plugin's Overworld dimension which will make travel annoying
- There is also a custom world generator for the End dimension that makes it look like a colosseum sorta
- Creeper explosions are much larger and cause fire
- Most mobs spawn with Strength, Resistance, and Speed
- Chests and Barrels cannot be crafted
- Endermen are automatically aggravated at any player that gets near them (except in the end)
- All Endermites and Silverfish spawn with Fire Resistance, and Strength
- Mobs that can spawn in the End include: Guardians, Vexes, Cave Spiders, Witches, Wither Skeletons, Strays, Vindicators, and Pillagers. Also ravagers riding bees.
- If you get set on fire there is no way to put yourself out
- Right-clicking a crafting table has a 1 in 15 chance to make the block explode
- Anvils will fall on every player in the overworld every once in a while
- Furnaces have a chance to be infested with bees
- Furnaces have a 1 in 32 chance to melt with each item that is smelted
- Witches and Ravagers can spawn in the Nether
- Having 29 separate items in your inventory will clear 12 of them
- Breaking leaves has a chance of either spawning a Witch riding a Bat, or a Witch riding an angry Bee
- Knockback is reversed on non-player entities
- Every once in a while you will have to answer a binomial multiplication question or solve a system of equations
- Phantoms can spawn in the Nether
- An endermite will spawn along with every normal mob in the Nether
- Breaking Netherrack turns the block into Deepslate before being broken. When the deepslate is broken, there may be lava
- You cannot place chest boats
- Crouching sets you on fire
- Wearing diamond armor will give you the Wither effect
- Trying to place a bed in the end will kill you
- Lava buckets are too hot to be picked up
- You cannot use your offhand
- Phantoms have Fire Resistance
- Your health must be between 12 and 17 in order to set your spawn
- Using a bed will set you on fire no matter what your health already is at
- Sprinting will give you a very high chance to trip and drop all of your items
- Taking too long in a menu will kill you for being too boring
- End frames will generate inside white Houses of Inadequacy. To get them, right click them and the item will drop. This will also spawn 12 silverfish.
- Pufferfish will drop near players every once in a while
- You can randomly get a ton of speed in the Nether
- Totems of Strays will spawn sometimes, and you can only damage the current top of the totem
- Entities that can randomly spawn around Overworld Players include: Blazes (will not drop rods), Ghasts, large Magma Cubes, large Phantoms, and Ravagers 
- Breaking a stone block can spawn: Bees, Cave Spiders, normal Spiders, Endermites, and small Magma Cubes
- Zombified Piglins become aggravated if you get too close
- Some mobs will spawn with pumpkins on their heads, if they hit you, you are locked to 9:16 aspect ratio until you die
</details>

# Tips
<details>
  <summary>Tip 1 - End Portal</summary>
You may have seen some white houses around. These have a chance to spawn with an end portal frame inside, that can be right-clicked to pick up. These houses will only appear in the white sections of the Overworld
</details>
<details>
  <summary>Tip 2 - Item Storage</summary>
You can use droppers to store items. Redstone was intentionally put in the "stone" layer for this purpose
</details>
<details>
  <summary>Tip 3 - Mining</summary>
Iron and Diamonds both start spawning exactly 10 blocks below the surface of the "stone" layer at that block's position. Because the surface of the stone layer changes, it is best to go as deep as possible.
</details>
<details>
  <summary>Tip 4 - Food</summary>
You may notice a lack of food when playing. Don't worry, there's plenty of cornbread around. Simply find a yellow tower and collect the Cornbread blocks, which you can craft into Cornbread
</details>

# Credits:
[FastNoiseLite](https://github.com/Auburn/FastNoiseLite) - Library used for noisemaps in world generation\
[StructureBlockLib](https://github.com/Shynixn/StructureBlockLib) - Library that helped translate .nbt structures into a .yml file to be read by the plugin