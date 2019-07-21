define(function(){
    var axios = require("axios");
    return {
        template: `
            <div>
                <b-modal id="modal-edit" :title="editTitle" no-close-on-backdrop @ok="onEditOk">
                    <form>
                         <div class="row form-group">
                            <label class="col-3 col-form-label col-form-label-sm text-right">name</label>
                            <div class="col-9">
                              <input type="text" class="form-control form-control-sm" v-model="editForm.name">
                            </div>
                         </div>
                    </form>
                    <template slot="modal-footer" slot-scope="{ ok, cancel }">
                        <b-button size="sm" variant="primary" @click="ok()">确认</b-button>
                        <b-button size="sm" variant="outline-secondary" @click="cancel()">取消</b-button>
                    </template>
                </b-modal>
                <div class="ac-form">
                  <form>
                      <div class="row form-group">
                        <label class="col-1 col-form-label col-form-label-sm text-right">ID</label>
                        <div class="col-3">
                          <input type="text" class="form-control form-control-sm" v-model="formData.id">
                        </div>
                        <label class="col-1 col-form-label col-form-label-sm text-right">Name</label>
                        <div class="col-3">
                          <input type="text" class="form-control form-control-sm" v-model="formData.name">
                        </div>
                        <label class="col-1 col-form-label col-form-label-sm text-right">birthday</label>
                        <div class="col-3">
                          <input type="date" class="form-control form-control-sm" v-model="formData.birthday">
                        </div>
                      </div>
                      <div class="row justify-content-end" style="padding-right:20px;">
                        <button type="button" class="btn btn-sm btn-outline-danger" v-on:click="onSearch();"><i class="fa fa-search"></i>查询</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary" v-on:click="onClear();" style="margin-left:10px;"><i class="fa fa-trash-o"></i>清空</button>
                        <b-button v-b-modal.modal-1 type="button" class="btn btn-sm btn-outline-success" style="margin-left:30px;"><i class="fa fa-plus"></i>新建</b-button>
                      </div>
                    </form>
                </div>
                <div class="ac-table">
                    <b-table striped hover small ref="dataTable" 
                        :items="dataProvider" 
                        :fields="fields" 
                        :busy="isBusy"
                        :sort-by.sync="pagination.sort"
                        :sort-desc.sync="pagination.sortDesc" 
                        per-page="0">
                        <div slot="table-busy" class="text-center text-danger my-2">
                            <b-spinner class="align-middle"></b-spinner>
                            <strong>Loading...</strong>
                        </div>
                        <template slot="op" slot-scope="data">
                            <span class="operator" v-on:click="opEdit(data.item.id);" v-b-tooltip.hover title="编辑" ><i class="fa fa-pencil"></i></span>
                            <span class="operator" v-on:click="opDelete(data.item.id);" v-b-tooltip.hover title="删除" ><i class="fa fa-trash"></i></span>
                        </template>
                    </b-table>
                    <b-pagination v-model="pagination.pageNo" :total-rows="pagination.rows" :per-page="pagination.limit" @change="onPageChange" align="right" size="sm"></b-pagination>
                </div>
            </div>
        `,
        data() {
            return {
                editTitle: '编辑',
                editModalShow: false,
                editForm: {},
                formData: {
                    id: '',name:'',birthday:''
                },
                pagination: {limit: 10, pageNo: 1, rows: 0, sort: 'id', sortDesc: true},
                isBusy: false,
                fields: [
                    {key: 'id', label: 'ID', sortable: true},
                    {key:'name', label: 'Name', sortable: false},
                    {key: 'birthday',label: 'birthday', sortable: true},
                    {key: 'op', thStyle:{'width': '80px'}}
                ]
            }
        },
        methods: {
            opEdit: function(id) {
                console.log(id)
                let promise = axios.get('/staff/StaffCtrl/getDetailInfo?id=' + id);
                promise.then((rsp) => {
                    this.editForm = rsp.data.data;
                    this.$bvModal.show('modal-edit');
                });
            },
            onEditOk: function(bvModalEvt) {
                console.log(this.editForm);
                let promise = axios.post('/staff/StaffCtrl/save', this.editForm);
                promise.then((rsp) => {
                    this.editForm = {};
                    this.onSearch();
                }).catch(error => {
                    bvModalEvt.preventDefault();
                });
            },
            dataProvider: function(ctx) {
                this.isBusy = true;
                let promise = axios.post('/staff/StaffCtrl/search', {data: this.formData, extLimit:this.pagination});
                return promise.then((rsp) => {
                    this.pagination.rows = rsp.data.total;
                    this.isBusy = false;
                    return rsp.data.data;
                }).catch(error => {
                    return [];
                })
            },
            onSearch: function () {
                this.pagination.pageNo = 1;
                this.$refs.dataTable.refresh()
            },
            onPageChange: function(page) {
                this.onSearch();
            },
            onClear: function () {
                this.formData = {};
                this.onSearch();
            }
        }
    }
});