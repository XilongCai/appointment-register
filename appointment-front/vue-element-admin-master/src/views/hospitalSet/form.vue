<template>
  <div class="app-container">
    医院设置添加
    <el-form label-width="120px">
      <el-form-item label="医院名称">
        <el-input v-model="hospitalSet.hosname" />
      </el-form-item>
      <el-form-item label="医院编号">
        <el-input v-model="hospitalSet.hoscode" />
      </el-form-item>
      <el-form-item label="api基础路径">
        <el-input v-model="hospitalSet.apiUrl" />
      </el-form-item>
      <el-form-item label="联系人姓名">
        <el-input v-model="hospitalSet.contactsName" />
      </el-form-item>
      <el-form-item label="联系人手机">
        <el-input v-model="hospitalSet.contactsPhone" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import hospset from '@/api/hosp/hospitalSet'
export default {
  data() {
    return {
      hospitalSet: {}
    }
  },

  created() {
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.getHospSet(id)
    }
  },

  methods: {
    saveOrUpdate() {
      if (!this.hospitalSet.id) {
        // 没有id，做添加
        this.save()
      } else {
        // 修改
        this.update()
      }
    },

    save() {
      hospset.saveHospSet(this.hospitalSet).then((response) => {
        this.$message({
          type: 'success',
          message: 'add successfully!'
        })
        this.$router.push({ path: '/hospSet/list' })
      })
    },

    getHospSet(id) {
      hospset.getHospSet(id).then((response) => {
        this.hospitalSet = response.data.data
      })
    },

    update() {
      hospset.updateHospSet(this.hospitalSet).then((response) => {
        this.$message({
          type: 'success',
          message: 'update successfully!'
        })
        this.$router.push({ path: '/hospSet/list' })
      })
    }
  }
}
</script>
