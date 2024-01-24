import {
    AdvancedFilterModule,
    ClientSideRowModelModule,
    ClipboardModule,
    ColumnsToolPanelModule,
    createGrid,
    ExcelExportModule,
    FiltersToolPanelModule,
    GridChartsModule,
    MasterDetailModule,
    MenuModule,
    MultiFilterModule,
    RangeSelectionModule,
    RichSelectModule,
    RowGroupingModule,
    ServerSideRowModelModule,
    SetFilterModule,
    SideBarModule,
    StatusBarModule,
    ViewportRowModelModule,
} from 'ag-grid-enterprise';

const gridModules = [
    AdvancedFilterModule,
    ClientSideRowModelModule,
    GridChartsModule,
    ClipboardModule,
    ColumnsToolPanelModule,
    ExcelExportModule,
    FiltersToolPanelModule,
    MasterDetailModule,
    MenuModule,
    RangeSelectionModule,
    RichSelectModule,
    RowGroupingModule,
    SetFilterModule,
    MultiFilterModule,
    ServerSideRowModelModule,
    SideBarModule,
    StatusBarModule,
    ViewportRowModelModule,
];

/**
 * @param {Object} initialGridData
 * @param {string} url
 * @returns {IServerSideDatasource}
 **/
function createServerSideDataSource(initialGridData, url) {
    let isInitialLoad = true;

    return {
        getRows: (params) => {
            if (isInitialLoad && canUseInitial(initialGridData, params)) {
                console.log('[Datasource] - using initial grid Data: ', initialGridData);
                isInitialLoad = false;
                params.success({
                    rowData: initialGridData.rows,
                    rowCount: initialGridData.total
                })
                return;
            }

            console.log('[Datasource] - rows requested by grid: ', params.request);
            fetch(url + '?' + new URLSearchParams({
                startRow: params.request.startRow,
                endRow: params.request.endRow,
            }))
                .then((response) => response.json())
                .then(function (data) {
                    /** @type {LoadSuccessParams} **/
                    const successData = {
                        rowData: data.rows,
                        rowCount: data.total
                    };
                    params.success(successData);
                })
                .catch((error) => {
                    console.error(`Failed to fetch: ${error}`);
                    params.fail();
                });
        }
    }
}

/**
 * @param {Object} initialGridData
 * @param {IServerSideGetRowsParams} params
 */
function canUseInitial(initialGridData, params) {
    return initialGridData != null
        && initialGridData.startRow === params.request.startRow
        && initialGridData.endRow === params.request.endRow
}

customElements.define("my-ag-grid", class extends HTMLElement {
    connectedCallback() {
        const gridSrc = this.getAttribute('src');

        const gridOptionsNode = this.querySelector('#gridOptions');
        const gridOptions = JSON.parse(gridOptionsNode.innerHTML);

        const gridDataNode = this.querySelector('#gridData');
        const initialGridData = JSON.parse(gridDataNode.innerHTML);

        const gridDiv = document.createElement("div");
        gridDiv.id = "disGrid"
        gridDiv.className = "ag-theme-quartz ag-theme-custom"
        gridDiv.style.height = "100%";
        this.appendChild(gridDiv);
        /** @type {GridApi} **/
        const gridApi = createGrid(gridDiv, gridOptions, {modules: gridModules});
        gridApi.setGridOption('serverSideDatasource', createServerSideDataSource(initialGridData, gridSrc));
    }
});

/**
 * @typedef {Object} Grid
 *
 * @param {(HTMLElement) => GridApi} createGrid
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/grid.ts}
 */

/**
 * @typedef {Object} GridApi
 */

/**
 * @typedef {Object} GridOptions
 * @template TData
 * @property {'infinite' | 'viewport' | 'clientSide' | 'serverSide'} [rowModelType]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/entities/gridOptions.ts}
 */

/**
 * @typedef {Object} ColumnVO
 * @property {string} id
 * @property {string} displayName
 * @property {string} [field]
 * @property {string} [aggFunc]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iColumnVO.ts}
 */

/**
 * @typedef {Object} IServerSideGetRowsRequest
 * @property {number | undefined} startRow - First row requested or undefined for all rows.
 * @property {number | undefined} endRow - Index after the last row required row or undefined for all rows.
 * @property {ColumnVO[]} rowGroupCols - Columns that are currently row grouped.
 * @property {ColumnVO[]} valueCols - Columns that have aggregations on them.
 * @property {ColumnVO[]} pivotCols - Columns that have pivot on them.
 * @property {boolean} pivotMode - Defines if pivot mode is on or off.
 * @property {string[]} groupKeys - What groups the user is viewing.
 * more properties in original
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} IServerSideDatasource
 * @property {(IServerSideGetRowsParams) => void} getRows
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} IServerSideGetRowsParams
 * @property {IServerSideGetRowsRequest} request
 * @property {(params: LoadSuccessParams) => void} success
 * @property {() => void} fail
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/interfaces/iServerSideDatasource.ts}
 */

/**
 * @typedef {Object} LoadSuccessParams
 * @param {any[]} rowData
 * @param {number} [rowCount]
 *
 * Original is the TypeScript file at {@link https://github.com/ag-grid/ag-grid/blob/latest/grid-community-modules/core/src/ts/rowNodeCache/rowNodeBlock.ts}
 */