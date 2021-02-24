<template>
  <div id="app">
    <h1>XD</h1>
    <table>
      <tbody>
        <tr v-for="(row, x) in tiles" :key="x">
          <td v-for="(tile, y) in row" :key="y">
            <div :class="'tile ' + tile.material"></div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style>
.tile {
  width: 50px;
  height: 50px;
  border: 2px solid #aaaaaa;
}

.tile.water {
  background: aqua;
}

.tile.mountain {
  background: black;
}

.tile.forest {
  background: lime;
}

.tile.farm {
  background: yellow;
}

.tile.village {
  background: red;
}

.tile.monster {
  background: purple;
}
</style>

<script>

const materials = ['empty', 'forest', 'water', 'farm', 'village', 'monster'];

const randomMaterial = () => materials[Math.floor(Math.random() * materials.length)];

const createRandomMap = () => {
  const map = [...Array(11)].map(() => Array(11));

  for (let y = 0; y < 11; y ++) {
    for (let x = 0; x < 11; x ++) {
      map[y][x] = {material: randomMaterial()}
    }
  }

  const mountains = [{x: 1, y: 1}];

  for (let mountain of mountains) {
    map[mountain.y][mountain.x] = { material: mountain };
  }

  return map;
}

export default {
  name: 'App',
  data: () => ({
    ruins: [],
    tiles: createRandomMap()
  }),
  components: {
  }
}
</script>
