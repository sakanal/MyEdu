<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>

    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-button type="text" @click="dialogChapterFormVisible = true">添加章节</el-button>

    <el-tree
      ref="tree2"
      :data="data"
      :props="defaultProps"
      :filter-node-method="filterNode"
      :expand-on-click-node="false"
      class="filter-tree"
      default-expand-all>
      <span slot-scope="{ node, data }" class="custom-tree-node">
        <span>{{ node.label }}</span>
        <span>
          <span v-if="data.isParent === true">
            <el-button type="text" size="mini" @click="openEditVideo(data,false)">添加小节</el-button>
          </span>

          <!--章节管理按钮-->
          <span v-if="data.isParent === true">
            <el-button type="text" size="mini" @click="openEditChapter(data)">编辑</el-button>
            <el-button type="text" size="mini" @click="removeChapter(node, data)">删除</el-button>
          </span>
          <!--小节管理按钮-->
          <span v-else>
          <el-button type="text" size="mini" @click="openEditVideo(data,true)">编辑小节</el-button>
            <el-button type="text" size="mini" @click="removeVideo(node, data)">删除</el-button>
          </span>

        </span>
      </span>
    </el-tree>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下 一步</el-button>
      </el-form-item>
    </el-form>

    <el-dialog :visible.sync="dialogChapterFormVisible" title="章节信息">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateChapter">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogVideoFormVisible" title="课时信息">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right" />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="1">免费</el-radio>
            <el-radio :label="0">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <el-upload
            :action="BASE_API+'/vod/file/uploadVideo'"
            name="multipartFile"
            :on-exceed="uploadVideoExceed"
            :on-success="uploadVideoSuccess"
            :before-remove="removeBeforeVideo"
            :on-remove="removeUploadVideo"
            :file-list="fileList"
            :limit="1"
            class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">
                最大支持1G，支持大部分格式上传
                <i class="el-icon-question"/>
              </div>
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import chapter from '@/api/course/chapter'
import video from '@/api/course/video'
export default {
  data() {
    return {
      // isSave: true,
      filterText: '', // 搜索内容
      courseId: '', // 课程id
      data: [], // tree数据
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      dialogChapterFormVisible: false, // 章节模态框是否开启
      dialogVideoFormVisible: false, // 小节模态框是否开启
      chapter: { // 章节信息
        title: '',
        sort: 0
      },
      video: { // 小节信息
        title: '',
        sort: 0,
        isFree: 1,
        videoSourceId: '',
        videoOriginalName: ''
      },

      fileList: [],
      // BASE_API: process.env.VUE_APP_BASE_API,
      BASE_API: process.env.VUE_APP_VOD_BASE_API,

      saveBtnDisabled: false // 是否隐藏下一步的按钮
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      this.getAllChapter(this.$route.params.id)
    }
  },
  methods: {
    // 获取该课程的所有章节和小节
    getAllChapter(courseId) {
      chapter.getAllChapter(courseId)
        .then(response => {
          this.data = response.data
          // this.isSave = this.data === null
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 搜索
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    },
    // <!-- -------- 章节管理 -------- -->
    // 添加章节
    addChapter() {
      this.chapter.courseId = this.courseId
      chapter.addChapter(this.chapter)
        .then(response => {
          this.dialogChapterFormVisible = false
          this.$message.success('添加章节成功')
          this.chapter.sort = 0
          this.chapter.title = ''
          this.getAllChapter(this.courseId)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 修改章节信息
    updateChapter() {
      chapter.updateChapter(this.chapter)
        .then(response => {
          this.dialogChapterFormVisible = false
          this.$message.success('修改章节成功')
          this.chapter.id = ''
          this.chapter.sort = 0
          this.chapter.title = ''
          this.getAllChapter(this.courseId)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 开启章节修改模态框，修改和添加共用，修改会在开启前获取该章节原始信息，其中有只有后台可以获取到的id
    openEditChapter(data) {
      this.dialogChapterFormVisible = true
      chapter.getChapterById(data.id)
        .then(response => {
          this.chapter.id = response.data.id
          this.chapter.title = response.data.title
          this.chapter.sort = response.data.sort
          this.chapter.courseId = response.data.courseId
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 删除章节
    removeChapter(node, data) {
      this.$confirm('此操作将永久删除该章节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        chapter.removeChapter(data.id)
          .then(response => {
            this.$message.success('成功删除该章节')
            this.getAllChapter(this.courseId)
          })
          .catch(error => {
            console.log(error)
          })
      })
    },
    // 通过this.chapter中是否有后台传过来的章节id，判断是添加或修改
    saveOrUpdateChapter() {
      if (!this.chapter.id) {
        this.addChapter()
      } else {
        this.updateChapter()
      }
    },
    // <!-- -------- 小节管理 -------- -->
    // 添加小节
    addVideo(chapterId) {
      this.video.courseId = this.courseId
      video.addVideo(this.video)
        .then(response => {
          this.$message.success('添加小节成功')
          this.dialogVideoFormVisible = false
          this.video.chapterId = ''
          this.video.title = ''
          this.video.sort = 0
          this.video.isFree = 1
          this.video.videoSourceId = ''
          this.fileList = []
          this.getAllChapter(this.courseId)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 修改小节信息
    updateVideo() {
      console.log(this.video)
      video.updateVideo(this.video)
        .then(response => {
          this.dialogVideoFormVisible = false
          this.$message.success('修改小节成功')
          this.video.title = ''
          this.video.sort = 0
          this.video.isFree = 1
          this.video.videoSourceId = null
          this.fileList = []
          this.getAllChapter(this.courseId)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 开启小节修改模态框，修改和添加共用，修改会在开启前获取该小节原始信息，其中有只有后台可以获取到的id
    openEditVideo(data, isVideo) {
      this.video.id = ''
      this.video.chapterId = data.id //
      this.fileList = []
      this.dialogVideoFormVisible = true
      if (isVideo) {
        video.getVideoById(data.id)
          .then(response => {
            this.video = response.data
            const videoSourceId = this.video.videoSourceId
            if (videoSourceId === '' || videoSourceId === null) {
              this.video.videoSourceId = null
              this.video.videoOriginalName = null
            } else {
              this.fileList = [{ 'name': this.video.videoOriginalName }]
            }
          })
          .catch(error => {
            console.log(error)
          })
      } else {
        this.video.title = ''
        this.video.sort = 0
        this.video.isFree = 0
      }
    },
    // 删除小节
    removeVideo(node, data) {
      this.$confirm('此操作将永久删除该小节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        video.removeVideo(data.id)
          .then(response => {
            this.$message.success('成功删除该小节')
            this.getAllChapter(this.courseId)
          })
          .catch(error => {
            console.log(error)
          })
      })
    },
    // 通过this.video中是否有后台传过来的小节id，判断是修改或添加
    saveOrUpdateVideo() {
      if (!this.video.id) {
        this.addVideo()
      } else {
        this.updateVideo()
      }
    },
    // <!-- -------- 上传视频 -------- -->
    uploadVideoExceed() {
      this.$message.warning('想要重新上传视频，请先删除已上传的所有视频')
    },
    uploadVideoSuccess(response, file, fileList) {
      this.video.videoSourceId = response.data
      this.video.videoOriginalName = file.name
    },
    removeBeforeVideo(file, fileList) {
      return this.$confirm(`确定删除${file.name}?`)
    },
    removeUploadVideo() {
      video.removeAliyunVideo(this.video.videoSourceId)
        .then(response => {
          this.$message.success('删除视频成功')
          this.video.videoSourceId = ''
          this.video.videoOriginalName = ''
        })
        .catch(error => console.log(error))
    },
    testCors() {
      video.testCors()
        .then(response => console.log(response))
    },
    // 跳转到上一步
    previous() {
      this.$router.push({ path: '/course/edit/' + this.courseId })
    },
    // 跳转到下一步
    next() {
      // 跳转到第三步
      this.$router.push({ path: '/course/publish/' + this.courseId })
    }
  }
}
</script>

<style scoped>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
